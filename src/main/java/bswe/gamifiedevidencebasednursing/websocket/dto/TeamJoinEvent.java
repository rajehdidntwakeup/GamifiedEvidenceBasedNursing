package bswe.gamifiedevidencebasednursing.websocket.dto;

import java.time.Instant;

/**
 * DTO for team join events sent via WebSocket.
 */
public record TeamJoinEvent(
    Long teamId,
    String teamName,
    Long gameId,
    String joinedBy,
    Instant timestamp
) {
  public TeamJoinEvent {
    if (timestamp == null) {
      timestamp = Instant.now();
    }
  }
}
