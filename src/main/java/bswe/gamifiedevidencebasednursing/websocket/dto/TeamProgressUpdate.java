package bswe.gamifiedevidencebasednursing.websocket.dto;

import bswe.gamifiedevidencebasednursing.domain.enums.Location;
import bswe.gamifiedevidencebasednursing.domain.enums.Status;

import java.time.Instant;

/**
 * DTO for team progress updates sent via WebSocket.
 */
public record TeamProgressUpdate(
    Long teamId,
    String teamName,
    Location currentLocation,
    Status status,
    Integer score,
    Integer timerRemaining,
    String currentQuestion,
    Boolean isWinner,
    Instant timestamp
) {
  public TeamProgressUpdate {
    if (timestamp == null) {
      timestamp = Instant.now();
    }
  }
}
