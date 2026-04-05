package bswe.gamifiedevidencebasednursing.domain.dto;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * DTO for admin analytics and statistics.
 */
public record AdminAnalyticsDto(
    // Game statistics
    int totalGames,
    int activeGames,
    int completedGames,
    int totalTeams,
    int totalPlayers,

    // Performance metrics
    double averageCompletionTime,
    double averageScore,
    double successRate, // percentage of teams completing game

    // Mission breakdown
    List<MissionStatsDto> missionStats,

    // Question difficulty
    List<QuestionDifficultyDto> questionDifficulty,

    // Time-based stats
    Map<String, Integer> gamesPerDay,

    // Leaderboard
    List<TopTeamDto> topTeams,

    // Time range
    Instant startTime,
    Instant endTime
) {
  /**
   * Statistics per mission.
   */
  public record MissionStatsDto(
      String mission,
      int totalAttempts,
      int completions,
      double averageScore,
      double averageTime
  ) {
  }

  /**
   * Question difficulty metrics.
   */
  public record QuestionDifficultyDto(
      Long questionId,
      String title,
      int totalAttempts,
      int correctAttempts,
      double successRate,
      double averageTimeToAnswer
  ) {
  }

  /**
   * Top performing team.
   */
  public record TopTeamDto(
      Long teamId,
      String mission,
      int score,
      int completionTimeMinutes,
      Instant completedAt
  ) {
  }
}
