package bswe.gamifiedevidencebasednursing.controller;

import bswe.gamifiedevidencebasednursing.domain.dto.LeaderboardEntryDto;
import bswe.gamifiedevidencebasednursing.domain.dto.LeaderboardFilterDto;
import bswe.gamifiedevidencebasednursing.service.LeaderboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * REST controller for leaderboard operations.
 * Provides real-time rankings and historical data.
 */
@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {

  private final LeaderboardService leaderboardService;

  public LeaderboardController(LeaderboardService leaderboardService) {
    this.leaderboardService = leaderboardService;
  }

  /**
   * Get live leaderboard for a game (public access).
   *
   * @param gameId the game ID
   * @return sorted leaderboard entries
   */
  @GetMapping("/games/{gameId}")
  public ResponseEntity<List<LeaderboardEntryDto>> getGameLeaderboard(
      @PathVariable Long gameId) {
    List<LeaderboardEntryDto> leaderboard = leaderboardService.getGameLeaderboard(gameId);
    return ResponseEntity.ok(leaderboard);
  }

  /**
   * Get leaderboard with position highlighting for authenticated user.
   *
   * @param gameId    the game ID
   * @param teamId    the team ID to highlight
   * @param principal the authenticated user
   * @return leaderboard with user's position marked
   */
  @GetMapping("/games/{gameId}/my-position")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<LeaderboardEntryDto> getMyPosition(
      @PathVariable Long gameId,
      @RequestParam Long teamId,
      Principal principal) {
    LeaderboardEntryDto position = leaderboardService.getTeamPosition(gameId, teamId);
    return ResponseEntity.ok(position);
  }

  /**
   * Get top N teams from a game.
   *
   * @param gameId the game ID
   * @param limit  number of entries (default 10)
   * @return top teams
   */
  @GetMapping("/games/{gameId}/top")
  public ResponseEntity<List<LeaderboardEntryDto>> getTopTeams(
      @PathVariable Long gameId,
      @RequestParam(defaultValue = "10") int limit) {
    List<LeaderboardEntryDto> topTeams = leaderboardService.getTopTeams(gameId, limit);
    return ResponseEntity.ok(topTeams);
  }

  /**
   * Get global leaderboard across all games.
   *
   * @return global rankings
   */
  @GetMapping("/global")
  public ResponseEntity<List<LeaderboardEntryDto>> getGlobalLeaderboard() {
    List<LeaderboardEntryDto> leaderboard = leaderboardService.getGlobalLeaderboard();
    return ResponseEntity.ok(leaderboard);
  }

  /**
   * Get global leaderboard filtered by mission.
   *
   * @param mission the mission name
   * @return mission-specific rankings
   */
  @GetMapping("/global/mission/{mission}")
  public ResponseEntity<List<LeaderboardEntryDto>> getMissionLeaderboard(
      @PathVariable String mission) {
    List<LeaderboardEntryDto> leaderboard = leaderboardService.getMissionLeaderboard(mission);
    return ResponseEntity.ok(leaderboard);
  }

  /**
   * Get leaderboard with filters.
   *
   * @param filters filter criteria
   * @return filtered leaderboard
   */
  @PostMapping("/filter")
  public ResponseEntity<List<LeaderboardEntryDto>> getFilteredLeaderboard(
      @RequestBody LeaderboardFilterDto filters) {
    List<LeaderboardEntryDto> leaderboard = leaderboardService.getFilteredLeaderboard(filters);
    return ResponseEntity.ok(leaderboard);
  }

  /**
   * Get ranking history for a team.
   *
   * @param gameId the game ID
   * @param teamId the team ID
   * @return historical positions
   */
  @GetMapping("/games/{gameId}/teams/{teamId}/history")
  public ResponseEntity<List<LeaderboardEntryDto.HistoryEntry>> getTeamHistory(
      @PathVariable Long gameId,
      @PathVariable Long teamId) {
    List<LeaderboardEntryDto.HistoryEntry> history = leaderboardService.getTeamHistory(gameId, teamId);
    return ResponseEntity.ok(history);
  }

  /**
   * Get rankings around a team (neighbors).
   *
   * @param gameId   the game ID
   * @param teamId   the team ID
   * @param range    number of teams above/below (default 2)
   * @return leaderboard with neighbors
   */
  @GetMapping("/games/{gameId}/teams/{teamId}/neighbors")
  public ResponseEntity<List<LeaderboardEntryDto>> getTeamNeighbors(
      @PathVariable Long gameId,
      @PathVariable Long teamId,
      @RequestParam(defaultValue = "2") int range) {
    List<LeaderboardEntryDto> neighbors = leaderboardService.getTeamNeighbors(gameId, teamId, range);
    return ResponseEntity.ok(neighbors);
  }

  /**
   * Get live updates via WebSocket subscription info.
   *
   * @param gameId the game ID
   * @return WebSocket subscription details
   */
  @GetMapping("/games/{gameId}/live")
  public ResponseEntity<LeaderboardLiveInfoDto> getLiveSubscriptionInfo(
      @PathVariable Long gameId) {
    LeaderboardLiveInfoDto info = new LeaderboardLiveInfoDto(
        "/topic/game/" + gameId + "/leaderboard",
        "/topic/game/" + gameId + "/score-updates",
        "Subscribe to these STOMP topics for live updates"
    );
    return ResponseEntity.ok(info);
  }

  /**
   * DTO for live leaderboard subscription info.
   */
  public record LeaderboardLiveInfoDto(
      String leaderboardTopic,
      String scoreUpdatesTopic,
      String description
  ) {
  }
}
