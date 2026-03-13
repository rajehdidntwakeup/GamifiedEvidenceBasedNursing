package bswe.gamifiedevidencebasednursing.domain.dto;

import bswe.gamifiedevidencebasednursing.domain.enums.Location;
import bswe.gamifiedevidencebasednursing.domain.enums.Mission;
import bswe.gamifiedevidencebasednursing.domain.enums.Status;

import java.time.Instant;
import java.util.List;

/**
 * DTO for admin team management.
 */
public record AdminTeamDto(
    Long id,
    Mission mission,
    Status status,
    Location currentLocation,
    int score,
    int questionsAnswered,
    int questionsCorrect,
    int hintsUsed,
    int timeRemaining,
    Instant startTime,
    Instant completionTime,
    Long gameId,
    String gamePassword,
    boolean isWinner,
    List<AdminQuestionAttemptDto> questionAttempts
) {
  /**
   * Record of question attempt.
   */
  public record AdminQuestionAttemptDto(
      Long questionId,
      String questionTitle,
      boolean correct,
      int pointsEarned,
      boolean hintUsed,
      Instant answeredAt
  ) {
  }
}
