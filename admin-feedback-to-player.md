# Plan: Real-Time Admin Feedback to Players via WebSocket

## Context

The `POST /api/admin/submission/analytics` endpoint allows an admin to approve or reject individual answers from a team's analytics submission. Currently, the `SubmissionService` only mutates database state (increments `room.progress` or clears `answerText` on rejection) and returns a plain-text `200 OK`. The team frontend has no way to know feedback was given until it polls or refreshes.

> **Codebase note:** The admin feedback DTOs (`AnalyticsSubmissionFeedbackDto` and `QuestionFeedbackDto`) live in `feature.admindashboard.dto.request`. The old `QusestionFeedbackDto` typo was corrected during this refactor.

The application already runs Spring WebSocket (STOMP) with a `SimpMessagingTemplate` broadcaster (`AnalyticsNotificationService`) used to push admin notifications on the topic `/topic/analytics/submissions` when a team submits answers. We will extend this pattern to push feedback results back to the affected team in real time.

---

## Step 1 — Fix the Missing Room Persistence Bug

**File:** `src/main/java/bswe/gamifiedevidencebasednursing/feature/admindashboard/service/SubmissionService.java`

`SubmissionService.analyticsSubmissionFeedback(...)` calls `room.setProgress(room.getProgress() + 20)` when an answer is approved, but it **never saves the `Room` entity**. The rejected answers are saved individually, but the room's progress change is lost on transaction commit.

**Action:**
- At the end of the method (after processing all questions), call `roomRepository.save(room)`.

```java
roomRepository.save(room);
return ResponseEntity.ok("Feedback submitted successfully");
```

> This must happen before the WebSocket payload is constructed so the outbound DTO reflects the latest persisted state.

---

## Step 2 — Create the Feedback Notification DTO

**New file:** `src/main/java/bswe/gamifiedevidencebasednursing/feature/roomofanalytics/dto/response/AnalyticsFeedbackDto.java`

This DTO is what the team frontend receives when the admin finishes reviewing their submission.

```java
package bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response;

import java.time.Instant;
import java.util.List;

public record AnalyticsFeedbackDto(
    Long roomId,
    Integer progress,
    Instant feedbackAt,
    List<QuestionFeedbackResultDto> questions
) {}
```

**New file:** `src/main/java/bswe/gamifiedevidencebasednursing/feature/roomofanalytics/dto/response/QuestionFeedbackResultDto.java`

```java
package bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response;

public record QuestionFeedbackResultDto(
    Long questionId,
    boolean approved,
    String answerText   // null when rejected so the UI can blank the field
) {}
```

> Use `record` types to stay consistent with the existing `AdminNotificationDto` and `AnswerDetailDto` in the same package.

---

## Step 3 — Extend the WebSocket Notification Service

**File:** `src/main/java/bswe/gamifiedevidencebasednursing/feature/roomofanalytics/service/AnalyticsNotificationService.java`

Add a second broadcast method that targets a **room-specific topic** so every client subscribed to that room receives the feedback instantly.

```java
public void notifyTeam(AnalyticsFeedbackDto feedback) {
    messagingTemplate.convertAndSend(
        "/topic/rooms/" + feedback.roomId() + "/feedback",
        feedback
    );
}
```

> Why `/topic/rooms/{roomId}/feedback`?
> - The frontend already knows its `roomId`.
> - No extra principal-to-user mapping is required (the `/user` prefix is configured but unused).
> - Multiple players on the same team can all subscribe and receive the update simultaneously.

---

## Step 4 — Wire the WebSocket Push into `SubmissionService`

**File:** `src/main/java/bswe/gamifiedevidencebasednursing/feature/admindashboard/service/SubmissionService.java`

1. Inject `AnalyticsNotificationService` into the constructor.
2. After persisting the room and clearing rejected answers, build `AnalyticsFeedbackDto`.
3. Call `analyticsNotificationService.notifyTeam(...)`.

**Refactored flow inside `analyticsSubmissionFeedback(...)`:**

```java
public ResponseEntity<String> analyticsSubmissionFeedback(
        AnalyticsSubmissionFeedbackDto dto) {

    Optional<Room> optionalRoom = roomRepository.findById(dto.getRoomId());
    if (optionalRoom.isEmpty()) {
        throw new IllegalArgumentException("Room not found");
    }

    Room room = optionalRoom.get();
    List<QuestionFeedbackResultDto> results = new ArrayList<>();

    for (QuestionFeedbackDto q : dto.getQuestions()) {
        if (q.getAnswer() == null) {
            throw new IllegalArgumentException("Answer cannot be null");
        }

        if (q.isApproved()) {
            room.setProgress(room.getProgress() + 20);
            results.add(new QuestionFeedbackResultDto(q.getQuestionId(), true, q.getAnswer()));
        } else {
            OpenQuestionAnswer oqa = openQuestionAnswerRepository
                    .findByRoomIdAndQuestionId(room.getId(), q.getQuestionId());
            if (oqa == null) {
                throw new IllegalArgumentException("Open question answer not found");
            }
            oqa.setAnswerText(null);
            openQuestionAnswerRepository.save(oqa);
            results.add(new QuestionFeedbackResultDto(q.getQuestionId(), false, null));
        }
    }

    roomRepository.save(room);

    AnalyticsFeedbackDto feedback = new AnalyticsFeedbackDto(
            room.getId(),
            room.getProgress(),
            Instant.now(),
            results
    );
    analyticsNotificationService.notifyTeam(feedback);

    return ResponseEntity.ok("Feedback submitted successfully");
}
```

**Why keep the DTO construction in `SubmissionService` and not a separate mapper?**
> The existing codebase does not use a separate mapper layer for analytics DTOs (`RoomOfAnalyticsService` builds `AdminNotificationDto` inline). Following the same style keeps the change minimal and consistent.

**Import cleanup:**
- `AnalyticsSubmissionFeedbackDto` and `QuestionFeedbackDto` come from `bswe.gamifiedevidencebasednursing.feature.admindashboard.dto.request`.
- Remove the unused `import bswe.gamifiedevidencebasednursing.domain.Answer;` from `SubmissionService.java`.

---

## Step 5 — Update the OpenAPI Specification

**File:** `openapi.yaml`

### 5a — Document the new topic

Under the existing `## WebSockets` block in `info.description` (around line 31-37), append:

```yaml
      - `/topic/rooms/{roomId}/feedback`: Teams can subscribe to this topic to receive real-time feedback when an admin approves or rejects their analytics answers. Replace `{roomId}` with the team's actual room ID.
```

### 5b — Add the new schemas

Append under `components.schemas`:

```yaml
    AnalyticsFeedbackDto:
      type: object
      description: Real-time feedback sent to a team after admin reviews their analytics submission
      properties:
        roomId:
          type: integer
          format: int64
          example: 3
        progress:
          type: integer
          example: 60
        feedbackAt:
          type: string
          format: date-time
          example: "2026-05-01T14:30:00Z"
        questions:
          type: array
          items:
            $ref: '#/components/schemas/QuestionFeedbackResultDto'

    QuestionFeedbackResultDto:
      type: object
      description: Result for a single question after admin review
      properties:
        questionId:
          type: integer
          format: int64
          example: 1
        approved:
          type: boolean
          example: true
        answerText:
          type: string
          description: The accepted answer text; null when rejected
          example: "Increased heart rate"
```

---

## Step 6 — Frontend Subscription Guide

The team frontend should subscribe to the room-specific feedback topic as soon as it knows its `roomId` (typically right after entering the Room of Analytics).

**STOMP subscription example (JavaScript / TypeScript):**

```javascript
const roomId = 3; // obtained from the room state
stompClient.subscribe(`/topic/rooms/${roomId}/feedback`, (message) => {
    const feedback = JSON.parse(message.body);
    updateProgress(feedback.progress);
    feedback.questions.forEach(q => {
        if (!q.approved) {
            clearAnswerField(q.questionId);   // force player to re-type
            showRejectionNotice(q.questionId);
        } else {
            markApproved(q.questionId);
        }
    });
});
```

> No additional JWT handling is needed beyond the existing `Authorization` header in the STOMP `CONNECT` frame; `WebSocketAuthInterceptor` already validates tokens for all inbound connections.

---

## Step 7 — Testing Checklist

| # | Test | Expected Result |
|---|------|----------------|
| 1 | Submit analytics answers as a team, then approve one and reject another as admin | Team UI receives a single WebSocket message containing both results and the updated `progress` |
| 2 | Reject an answer | `answerText` in the feedback DTO is `null`; the corresponding input field on the team UI clears |
| 3 | Approve two answers for a room with `progress = 40` | `progress` in the feedback DTO is `80`; DB row for the room reflects `80` after the request |
| 4 | Open two browser tabs for the same team (same `roomId`) | Both tabs receive the feedback message simultaneously |
| 5 | Send feedback for a non-existent `roomId` | `400` error; no WebSocket message is emitted |
| 6 | Unsubscribe and re-subscribe on page refresh | Previous messages are not replayed (simple broker has no persistence; this is acceptable for this use case) |

---

## Summary of Files to Change

| File | Action |
|------|--------|
| `SubmissionService.java` | Inject `AnalyticsNotificationService`; build and emit `AnalyticsFeedbackDto`; add `roomRepository.save(room)` |
| `AnalyticsNotificationService.java` | Add `notifyTeam(AnalyticsFeedbackDto)` method |
| `AnalyticsFeedbackDto.java` | **New** — feedback payload for the team |
| `QuestionFeedbackResultDto.java` | **New** — per-question result inside the payload |
| `openapi.yaml` | Document `/topic/rooms/{roomId}/feedback` and the two new schemas |

No changes are required to `WebSocketConfig.java`, `WebSocketAuthInterceptor.java`, or `SecurityConfig.java`; the existing infrastructure already supports broadcasting to arbitrary `/topic/*` destinations.
