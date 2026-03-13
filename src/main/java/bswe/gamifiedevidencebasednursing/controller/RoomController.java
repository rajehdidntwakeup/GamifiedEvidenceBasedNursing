package bswe.gamifiedevidencebasednursing.controller;

import bswe.gamifiedevidencebasednursing.domain.dto.QuestionDto;
import bswe.gamifiedevidencebasednursing.domain.dto.request.SubmitAnswerRequest;
import bswe.gamifiedevidencebasednursing.domain.dto.response.RoomStatusResponse;
import bswe.gamifiedevidencebasednursing.domain.dto.response.SubmitAnswerResponse;
import bswe.gamifiedevidencebasednursing.service.RoomService;
import bswe.gamifiedevidencebasednursing.websocket.service.WebSocketService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * REST controller for room operations and question flow.
 * Handles room status, questions, and answer submissions.
 */
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

  private final RoomService roomService;
  private final WebSocketService webSocketService;

  public RoomController(RoomService roomService, WebSocketService webSocketService) {
    this.roomService = roomService;
    this.webSocketService = webSocketService;
  }

  /**
   * Get room status and questions for a team.
   *
   * @param roomId    the room ID
   * @param teamId    the team ID
   * @param principal the authenticated user
   * @return room status with questions
   */
  @GetMapping("/{roomId}/status")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<RoomStatusResponse> getRoomStatus(
      @PathVariable Long roomId,
      @RequestParam Long teamId,
      Principal principal) {
    RoomStatusResponse status = roomService.getRoomStatus(roomId, teamId);
    return ResponseEntity.ok(status);
  }

  /**
   * Get questions for a room.
   *
   * @param roomId    the room ID
   * @param teamId    the team ID
   * @param principal the authenticated user
   * @return list of questions
   */
  @GetMapping("/{roomId}/questions")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<List<QuestionDto>> getRoomQuestions(
      @PathVariable Long roomId,
      @RequestParam Long teamId,
      Principal principal) {
    List<QuestionDto> questions = roomService.getQuestionsForRoom(roomId, teamId);
    return ResponseEntity.ok(questions);
  }

  /**
   * Submit an answer for a question.
   *
   * @param roomId    the room ID
   * @param request   the answer submission request
   * @param principal the authenticated user
   * @return submission result
   */
  @PostMapping("/{roomId}/submit-answer")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<SubmitAnswerResponse> submitAnswer(
      @PathVariable Long roomId,
      @Valid @RequestBody SubmitAnswerRequest request,
      Principal principal) {
    SubmitAnswerResponse response = roomService.submitAnswer(roomId, request);

    // Broadcast to other teams via WebSocket
    webSocketService.sendAnswerSubmittedEvent(
        request.teamId(), // This should be gameId - adjust in service
        new bswe.gamifiedevidencebasednursing.websocket.dto.AnswerSubmittedEvent(
            request.teamId(),
            principal.getName(),
            roomId,
            request.questionId(),
            response.correct(),
            response.pointsEarned(),
            response.newTotalScore(),
            response.feedback(),
            null
        )
    );

    return ResponseEntity.ok(response);
  }

  /**
   * Get hint for a question (costs points).
   *
   * @param roomId     the room ID
   * @param teamId     the team ID
   * @param questionId the question ID
   * @param principal  the authenticated user
   * @return hint text
   */
  @PostMapping("/{roomId}/questions/{questionId}/hint")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<String> getHint(
      @PathVariable Long roomId,
      @RequestParam Long teamId,
      @PathVariable Long questionId,
      Principal principal) {
    String hint = roomService.getHint(roomId, teamId, questionId);
    return ResponseEntity.ok(hint);
  }

  /**
   * Skip a question (marks as failed).
   *
   * @param roomId     the room ID
   * @param teamId     the team ID
   * @param questionId the question ID
   * @param principal  the authenticated user
   * @return updated room status
   */
  @PostMapping("/{roomId}/questions/{questionId}/skip")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<RoomStatusResponse> skipQuestion(
      @PathVariable Long roomId,
      @RequestParam Long teamId,
      @PathVariable Long questionId,
      Principal principal) {
    RoomStatusResponse status = roomService.skipQuestion(roomId, teamId, questionId);
    return ResponseEntity.ok(status);
  }
}
