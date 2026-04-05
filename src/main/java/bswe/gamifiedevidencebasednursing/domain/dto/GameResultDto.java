package bswe.gamifiedevidencebasednursing.domain.dto;

import java.time.Instant;
import java.util.List;

/**
 * DTO for complete game results.
 */
public record GameResultDto(
    Long gameId,
    Instant startTime,
    Instant endTime,
    long durationMinutes,
    Long winnerId,
    String winnerName,
    List<TeamResultDto> teamResults
) {
  /**
   * Individual team result.
   */
  public record TeamResultDto(
      Long teamId,
      String teamName,
      int score,
      int completionTimeMinutes,
      boolean isWinner,
      int finalRanking
  ) {
  }
}
