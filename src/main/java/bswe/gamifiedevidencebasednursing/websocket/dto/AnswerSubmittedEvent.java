package bswe.gamifiedevidencebasednursing.websocket.dto;

import java.time.Instant;

/**
 * DTO for answer submission events sent via WebSocket.
 */
public record AnswerSubmittedEvent(
    Long teamId,
    String teamName,
    Long roomId,
    Long questionId,
    Boolean isCorrect,
    Integer pointsEarned,
    Integer newTotalScore,
    String feedback,
    Instant timestamp
) {
  public AnswerSubmittedEvent {
    if (timestamp == null) {
      timestamp = Instant.now();
    }
  }
}
