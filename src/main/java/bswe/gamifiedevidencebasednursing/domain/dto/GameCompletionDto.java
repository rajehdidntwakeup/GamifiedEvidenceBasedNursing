package bswe.gamifiedevidencebasednursing.domain.dto;

import java.time.Instant;

/**
 * DTO for team game completion.
 */
public record GameCompletionDto(
    Long teamId,
    String teamName,
    int finalScore,
    int completionTimeMinutes,
    Long gameId,
    Instant completedAt,
    int finalRanking
) {
}
