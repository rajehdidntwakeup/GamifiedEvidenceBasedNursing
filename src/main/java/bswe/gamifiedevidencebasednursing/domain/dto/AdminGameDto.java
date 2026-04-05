package bswe.gamifiedevidencebasednursing.domain.dto;

import bswe.gamifiedevidencebasednursing.domain.enums.Status;

import java.time.Instant;
import java.util.List;

/**
 * DTO for admin game management.
 */
public record AdminGameDto(
    Long id,
    String password,
    Instant beginTime,
    Instant endTime,
    Status status,
    int teamCount,
    int activeTeams,
    int completedTeams,
    List<AdminTeamSummaryDto> teams,
    Long winningTeamId,
    String winningTeamName
) {
  /**
   * Summary of team for game list view.
   */
  public record AdminTeamSummaryDto(
      Long teamId,
      String mission,
      Status status,
      int score
  ) {
  }
}
