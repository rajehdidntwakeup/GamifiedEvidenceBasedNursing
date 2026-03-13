package bswe.gamifiedevidencebasednursing.domain.dto;

import java.time.Instant;

/**
 * DTO for filtering leaderboard results.
 */
public record LeaderboardFilterDto(
    Long gameId,
    String mission,
    Instant startDate,
    Instant endDate,
    Integer minScore,
    Integer maxScore,
    Boolean completedOnly,
    Boolean activeOnly,
    SortBy sortBy,
    SortOrder sortOrder,
    Integer limit,
    Integer offset
) {
  public LeaderboardFilterDto {
    if (sortBy == null) {
      sortBy = SortBy.SCORE;
    }
    if (sortOrder == null) {
      sortOrder = SortOrder.DESCENDING;
    }
    if (limit == null) {
      limit = 50;
    }
    if (offset == null) {
      offset = 0;
    }
  }

  /**
   * Sort criteria options.
   */
  public enum SortBy {
    SCORE,
    RANK,
    COMPLETION_TIME,
    ACCURACY,
    LAST_UPDATED
  }

  /**
   * Sort order options.
   */
  public enum SortOrder {
    ASCENDING,
    DESCENDING
  }
}
