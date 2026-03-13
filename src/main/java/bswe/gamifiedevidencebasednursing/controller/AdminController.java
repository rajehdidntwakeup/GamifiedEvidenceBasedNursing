package bswe.gamifiedevidencebasednursing.controller;

import bswe.gamifiedevidencebasednursing.domain.dto.AdminGameDto;
import bswe.gamifiedevidencebasednursing.domain.dto.AdminTeamDto;
import bswe.gamifiedevidencebasednursing.domain.dto.AdminAnalyticsDto;
import bswe.gamifiedevidencebasednursing.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * REST controller for admin dashboard operations.
 * Handles game management, team monitoring, and analytics.
 */
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

  private final AdminService adminService;

  public AdminController(AdminService adminService) {
    this.adminService = adminService;
  }

  /**
   * Get all games with summary statistics.
   *
   * @param principal the authenticated admin user
   * @return list of all games
   */
  @GetMapping("/games")
  public ResponseEntity<List<AdminGameDto>> getAllGames(Principal principal) {
    List<AdminGameDto> games = adminService.getAllGames();
    return ResponseEntity.ok(games);
  }

  /**
   * Get detailed game information.
   *
   * @param gameId    the game ID
   * @param principal the authenticated admin user
   * @return game details
   */
  @GetMapping("/games/{gameId}")
  public ResponseEntity<AdminGameDto> getGameDetails(
      @PathVariable Long gameId,
      Principal principal) {
    AdminGameDto game = adminService.getGameDetails(gameId);
    return ResponseEntity.ok(game);
  }

  /**
   * Delete a game.
   *
   * @param gameId    the game ID to delete
   * @param principal the authenticated admin user
   * @return no content on success
   */
  @DeleteMapping("/games/{gameId}")
  public ResponseEntity<Void> deleteGame(
      @PathVariable Long gameId,
      Principal principal) {
    adminService.deleteGame(gameId);
    return ResponseEntity.noContent().build();
  }

  /**
   * Get all teams in a game.
   *
   * @param gameId    the game ID
   * @param principal the authenticated admin user
   * @return list of teams
   */
  @GetMapping("/games/{gameId}/teams")
  public ResponseEntity<List<AdminTeamDto>> getGameTeams(
      @PathVariable Long gameId,
      Principal principal) {
    List<AdminTeamDto> teams = adminService.getGameTeams(gameId);
    return ResponseEntity.ok(teams);
  }

  /**
   * Get team details.
   *
   * @param gameId    the game ID
   * @param teamId    the team ID
   * @param principal the authenticated admin user
   * @return team details
   */
  @GetMapping("/games/{gameId}/teams/{teamId}")
  public ResponseEntity<AdminTeamDto> getTeamDetails(
      @PathVariable Long gameId,
      @PathVariable Long teamId,
      Principal principal) {
    AdminTeamDto team = adminService.getTeamDetails(gameId, teamId);
    return ResponseEntity.ok(team);
  }

  /**
   * Force start a game.
   *
   * @param gameId    the game ID
   * @param principal the authenticated admin user
   * @return updated game status
   */
  @PostMapping("/games/{gameId}/start")
  public ResponseEntity<AdminGameDto> startGame(
      @PathVariable Long gameId,
      Principal principal) {
    AdminGameDto game = adminService.startGame(gameId);
    return ResponseEntity.ok(game);
  }

  /**
   * Force end a game.
   *
   * @param gameId    the game ID
   * @param principal the authenticated admin user
   * @return updated game status
   */
  @PostMapping("/games/{gameId}/end")
  public ResponseEntity<AdminGameDto> endGame(
      @PathVariable Long gameId,
      Principal principal) {
    AdminGameDto game = adminService.endGame(gameId);
    return ResponseEntity.ok(game);
  }

  /**
   * Reset team progress.
   *
   * @param gameId    the game ID
   * @param teamId    the team ID
   * @param principal the authenticated admin user
   * @return reset team status
   */
  @PostMapping("/games/{gameId}/teams/{teamId}/reset")
  public ResponseEntity<AdminTeamDto> resetTeamProgress(
      @PathVariable Long gameId,
      @PathVariable Long teamId,
      Principal principal) {
    AdminTeamDto team = adminService.resetTeamProgress(gameId, teamId);
    return ResponseEntity.ok(team);
  }

  /**
   * Get analytics for a game.
   *
   * @param gameId    the game ID
   * @param principal the authenticated admin user
   * @return game analytics
   */
  @GetMapping("/games/{gameId}/analytics")
  public ResponseEntity<AdminAnalyticsDto> getGameAnalytics(
      @PathVariable Long gameId,
      Principal principal) {
    AdminAnalyticsDto analytics = adminService.getGameAnalytics(gameId);
    return ResponseEntity.ok(analytics);
  }

  /**
   * Get global analytics across all games.
   *
   * @param principal the authenticated admin user
   * @return global analytics
   */
  @GetMapping("/analytics")
  public ResponseEntity<AdminAnalyticsDto> getGlobalAnalytics(Principal principal) {
    AdminAnalyticsDto analytics = adminService.getGlobalAnalytics();
    return ResponseEntity.ok(analytics);
  }

  /**
   * Export game results as CSV.
   *
   * @param gameId    the game ID
   * @param principal the authenticated admin user
   * @return CSV data
   */
  @GetMapping("/games/{gameId}/export")
  public ResponseEntity<String> exportGameResults(
      @PathVariable Long gameId,
      Principal principal) {
    String csv = adminService.exportGameResults(gameId);
    return ResponseEntity.ok()
        .header("Content-Type", "text/csv")
        .header("Content-Disposition", "attachment; filename=game-" + gameId + "-results.csv")
        .body(csv);
  }
}
