package bswe.gamifiedevidencebasednursing.domain.dto;

import java.time.Instant;

/**
 * DTO for leaderboard entries.
 */
public record LeaderboardEntryDto(
    Long teamId,
    String teamName,
    String mission,
    int rank,
    int score,
    int totalQuestions,
    int correctAnswers,
    int hintsUsed,
    int completionTimeMinutes,
    double accuracyPercentage,
    boolean isCurrentUser,
    Status status,
    Long gameId,
    String gamePassword,
    Instant lastUpdated
) {
  /**
   * Position change indicators.
   */
  public enum Status {
    IMPROVED,     // Moved up in ranking
    DECLINED,     // Moved down in ranking
    STABLE,       // Same position
    NEW_ENTRY     // Just joined leaderboard
  }

  /**
   * Historical ranking entry.
   */
  public record HistoryEntry(
      Instant timestamp,
      int rank,
      int score,
      String event // e.g., "Answered Q3 correctly", "Used hint"
  ) {
  }
}
