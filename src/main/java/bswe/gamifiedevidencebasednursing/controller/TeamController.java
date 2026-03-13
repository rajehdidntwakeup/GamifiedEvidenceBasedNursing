package bswe.gamifiedevidencebasednursing.controller;

import bswe.gamifiedevidencebasednursing.domain.dto.TeamDto;
import bswe.gamifiedevidencebasednursing.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * REST controller for team operations.
 * Handles team management and progress tracking.
 */
@RestController
@RequestMapping("/api/teams")
public class TeamController {

  private final TeamService teamService;

  public TeamController(TeamService teamService) {
    this.teamService = teamService;
  }

  /**
   * Get team by ID.
   *
   * @param teamId    the team ID
   * @param principal the authenticated user
   * @return team details
   */
  @GetMapping("/{teamId}")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<TeamDto> getTeam(
      @PathVariable Long teamId,
      Principal principal) {
    TeamDto team = teamService.getTeamById(teamId);
    return ResponseEntity.ok(team);
  }

  /**
   * Get all teams in a game.
   *
   * @param gameId    the game ID
   * @param principal the authenticated user
   * @return list of teams
   */
  @GetMapping("/game/{gameId}")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<List<TeamDto>> getTeamsByGame(
      @PathVariable Long gameId,
      Principal principal) {
    List<TeamDto> teams = teamService.getTeamsByGameId(gameId);
    return ResponseEntity.ok(teams);
  }

  /**
   * Get team progress.
   *
   * @param teamId    the team ID
   * @param principal the authenticated user
   * @return team progress
   */
  @GetMapping("/{teamId}/progress")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<TeamProgressResponse> getTeamProgress(
      @PathVariable Long teamId,
      Principal principal) {
    // This would return detailed progress
    return ResponseEntity.ok(new TeamProgressResponse(teamId, 0, 0, 0, false));
  }

  /**
   * DTO for team progress response.
   */
  public record TeamProgressResponse(
      Long teamId,
      int currentRoom,
      int totalScore,
      int questionsAnswered,
      boolean isWinner
  ) {
  }
}
