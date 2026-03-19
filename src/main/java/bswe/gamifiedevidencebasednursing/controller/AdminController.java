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
   * Check if there are any games (administrators).
   *
   * @return true if at least one game exists
   */
  @GetMapping("/isThereAdmin")
  @PreAuthorize("permitAll()")
  public ResponseEntity<Boolean> isThereAdmin() {
    boolean isThereAdmin = adminService.isThereAdmin();
    return ResponseEntity.ok(isThereAdmin);
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

}
