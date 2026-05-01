# Admin Notification on Room of Analytics Submission via WebSocket

## Goal
When a user submits answers in the Room of Analytics, admins receive a real-time WebSocket notification containing the submission details for validation and analysis.

---

## 1. Add WebSocket Configuration

File: `src/main/java/bswe/gamifiedevidencebasednursing/config/WebSocketConfig.java`

```java
package bswe.gamifiedevidencebasednursing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/queue");
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}
```

**Why STOMP:** Provides topic-based pub/sub and is natively supported by Spring's `spring-boot-starter-websocket` already in `pom.xml`.

---

## 2. Create Notification DTOs

### `AdminNotificationDto.java`
File: `src/main/java/bswe/gamifiedevidencebasednursing/feature/roomofanalytics/dto/response/AdminNotificationDto.java`

```java
package bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response;

import java.time.Instant;
import java.util.List;

public record AdminNotificationDto(
        Long submissionId,
        Long missionId,
        String missionName,
        Long roomId,
        String roomName,
        Instant submittedAt,
        List<AnswerDetailDto> answers
) {}
```

### `AnswerDetailDto.java`
File: `src/main/java/bswe/gamifiedevidencebasednursing/feature/roomofanalytics/dto/response/AnswerDetailDto.java`

```java
package bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response;

public record AnswerDetailDto(
        Long questionId,
        String questionText,
        String answerText
) {}
```

---

## 3. Create Notification Service

File: `src/main/java/bswe/gamifiedevidencebasednursing/feature/roomofanalytics/service/AnalyticsNotificationService.java`

```java
package bswe.gamifiedevidencebasednursing.feature.roomofanalytics.service;

import bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response.AdminNotificationDto;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsNotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public AnalyticsNotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notifyAdmin(AdminNotificationDto notification) {
        messagingTemplate.convertAndSend("/topic/analytics/submissions", notification);
    }
}
```

**Broadcast model:** All admins subscribe to `/topic/analytics/submissions`. If per-admin routing is needed later, switch to `/user/queue/...` with `convertAndSendToUser`.

---

## 4. Emit Notification on Submission

Modify: `src/main/java/bswe/gamifiedevidencebasednursing/feature/roomofanalytics/service/RoomOfAnalyticsService.java`

Add imports and update `submitAnalytics`:

```java
import bswe.gamifiedevidencebasednursing.domain.Mission;
import bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response.AdminNotificationDto;
import bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response.AnswerDetailDto;
import java.time.Instant;

// ... other existing imports

@Transactional
public ResponseEntity<String> submitAnalytics(SubmissionDto submissionDto) {
    Optional<Room> roomOptional = roomRepository.findById(submissionDto.getRoomId());
    if (roomOptional.isEmpty()) {
        throw new IllegalArgumentException("Room not found");
    }
    Room room = roomOptional.get();
    
    List<OpenQuestionAnswer> openQuestionAnswers = new ArrayList<>();
    for (OpenQuestionSubmissionDto openQuestionSubmissionDto : submissionDto.getOpenQuestions()) {
        Optional<Question> questionOptional = questionRepository.findById(openQuestionSubmissionDto.getQuestionId());
        if (questionOptional.isEmpty()) {
            throw new IllegalArgumentException("Question not found");
        }
        Question question = questionOptional.get();
        OpenQuestionAnswer openQuestionAnswer = new OpenQuestionAnswer();
        openQuestionAnswer.setRoom(room);
        openQuestionAnswer.setQuestion(question);
        openQuestionAnswer.setAnswerText(openQuestionSubmissionDto.getAnswer());
        openQuestionAnswers.add(openQuestionAnswer);
    }
    List<OpenQuestionAnswer> saved = openQuestionAnswerRepository.saveAll(openQuestionAnswers);

    // Emit notification to admins
    if (!saved.isEmpty()) {
        Mission mission = room.getTeam().getMission();
        AdminNotificationDto notification = new AdminNotificationDto(
                saved.get(0).getId(), // Using first answer ID as submission reference
                mission.getId(),
                mission.getName(),
                room.getId(),
                room.getLocation().getName(),
                Instant.now(),
                saved.stream().map(a -> new AnswerDetailDto(
                        a.getQuestion().getId(),
                        a.getQuestion().getTitle(),
                        a.getAnswerText()
                )).toList()
        );
        analyticsNotificationService.notifyAdmin(notification);
    }

    return ResponseEntity.ok("Analytics submitted successfully");
}
```

**Important:** Generate a dedicated `submissionBatchId` (UUID or DB sequence) instead of reusing the first answer ID if multiple answers belong to one submission.

---

## 5. Secure WebSocket Endpoints

Modify: `src/main/java/bswe/gamifiedevidencebasednursing/security/SecurityConfig.java`

Add authorization for WebSocket handshake and STOMP message destinations in `securityFilterChain`:

```java
.authorizeHttpRequests(auth -> auth
    // ... existing rules
    .requestMatchers("/ws/**").permitAll() // Handshake usually permitted, security handled in interceptor
    .requestMatchers("/topic/analytics/submissions").hasRole("ADMIN")
    .anyRequest().authenticated()
)
```

### Create WebSocket Interceptor

File: `src/main/java/bswe/gamifiedevidencebasednursing/config/WebSocketAuthInterceptor.java`

```java
package bswe.gamifiedevidencebasednursing.config;

import bswe.gamifiedevidencebasednursing.security.JwtService;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class WebSocketAuthInterceptor implements ChannelInterceptor {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public WebSocketAuthInterceptor(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (accessor != null && StompCommand.CONNECT.equals(accessor.getCommand())) {
            String authHeader = accessor.getFirstNativeHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                String username = jwtService.extractUsername(token);
                if (username != null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    if (jwtService.isTokenValid(token, userDetails)) {
                        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        accessor.setUser(auth);
                    }
                }
            }
        }
        return message;
    }
}
```

Register it:

```java
@Override
public void configureClientInboundChannel(ChannelRegistration registration) {
    registration.interceptors(webSocketAuthInterceptor);
}
```

---

## 6. Admin Frontend / Client Subscription

Admins connect to:

```javascript
const socket = new SockJS('/ws');
const stompClient = Stomp.over(socket);

stompClient.connect({"Authorization": "Bearer " + token}, frame => {
    stompClient.subscribe('/topic/analytics/submissions', message => {
        const notification = JSON.parse(message.body);
        console.log("New submission:", notification);
        renderSubmissionForValidation(notification);
    });
});
```

---

## 7. Validation & Markdown Export (Optional)

To support "create a markdown file" as requested:

File: `src/main/java/bswe/gamifiedevidencebasednursing/feature/roomofanalytics/service/SubmissionExportService.java`

```java
@Service
public class SubmissionExportService {

    public String toMarkdown(AdminNotificationDto dto) {
        StringBuilder sb = new StringBuilder();
        sb.append("# Submission ").append(dto.submissionId()).append("\n\n");
        sb.append("- **Mission:** ").append(dto.missionName()).append("\n");
        sb.append("- **Room:** ").append(dto.roomName()).append("\n");
        sb.append("- **Submitted At:** ").append(dto.submittedAt()).append("\n\n");
        sb.append("## Answers\n\n");
        for (AnswerDetailDto a : dto.answers()) {
            sb.append("### Q").append(a.questionId()).append(": ").append(a.questionText()).append("\n\n");
            sb.append(a.answerText()).append("\n\n");
        }
        return sb.toString();
    }

    public void saveMarkdownToFile(AdminNotificationDto dto, Path path) throws IOException {
        Files.writeString(path, toMarkdown(dto));
    }
}
```

Expose via REST for manual download or auto-generate on notification.

---

## 8. Testing

1. **Unit test:** Mock `SimpMessagingTemplate`, verify `convertAndSend` called with correct destination and DTO.
2. **Integration test:** Use `WebSocketTestClient` from `spring-boot-starter-websocket` test support or `StompClient` in tests.
3. **Security test:** Assert that non-ADMIN users subscribing to `/topic/analytics/submissions` receive `403` or are disconnected.

---

## Summary Checklist

| Step | File / Destination |
|------|-------------------|
| WebSocket config | `config/WebSocketConfig.java` |
| Notification DTOs | `dto/response/AdminNotificationDto.java`, `AnswerDetailDto.java` |
| Emitter service | `service/AnalyticsNotificationService.java` |
| Hook into submission | `service/RoomOfAnalyticsService.java` |
| Secure channels | `security/SecurityConfig.java`, `config/WebSocketAuthInterceptor.java` |
| Admin client sub | `/topic/analytics/submissions` |
| Markdown export | `service/SubmissionExportService.java` (optional) |
