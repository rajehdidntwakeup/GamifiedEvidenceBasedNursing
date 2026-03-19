package bswe.gamifiedevidencebasednursing.controller;

import bswe.gamifiedevidencebasednursing.domain.dto.GameCompletionDto;
import bswe.gamifiedevidencebasednursing.domain.dto.GameResultDto;
import bswe.gamifiedevidencebasednursing.service.GameCompletionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

/**
 * REST controller for game completion operations.
 * Handles team completion, winner detection, and final results.
 */
@RestController
@RequestMapping("/api/completion")
public class GameCompletionController {

  private final GameCompletionService completionService;

  public GameCompletionController(GameCompletionService completionService) {
    this.completionService = completionService;
  }

  /**
   * Check if a team has completed all rooms.
   *
   * @param teamId    the team ID
   * @param principal the authenticated user
   * @return true if complete
   */
  @GetMapping("/teams/{teamId}/status")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<Boolean> checkTeamCompletion(
      @PathVariable Long teamId,
      Principal principal) {
    boolean isComplete = completionService.isTeamComplete(teamId);
    return ResponseEntity.ok(isComplete);
  }


  /**
   * Check if a game is complete (all teams finished).
   *
   * @param gameId    the game ID
   * @param principal the authenticated user
   * @return game result if complete
   */
  @GetMapping("/games/{gameId}/check")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<Optional<GameResultDto>> checkGameCompletion(
      @PathVariable Long gameId,
      Principal principal) {
    Optional<GameResultDto> result = completionService.checkGameCompletion(gameId);
    return ResponseEntity.ok(result);
  }

  /**
   * Get final results for a completed game.
   *
   * @param gameId    the game ID
   * @param principal the authenticated user
   * @return game results
   */
  @GetMapping("/games/{gameId}/results")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<GameResultDto> getGameResults(
      @PathVariable Long gameId,
      Principal principal) {
    GameResultDto results = completionService.getGameResults(gameId);
    return ResponseEntity.ok(results);
  }

  /**
   * Force end a game (admin only).
   *
   * @param gameId    the game ID
   * @param principal the authenticated admin
   * @return game results
   */
  @PostMapping("/games/{gameId}/force-end")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<GameResultDto> forceEndGame(
      @PathVariable Long gameId,
      Principal principal) {
    GameResultDto results = completionService.forceEndGame(gameId);
    return ResponseEntity.ok(results);
  }

  /**
   * Get winner announcement for a game.
   *
   * @param gameId    the game ID
   * @param principal the authenticated user
   * @return winner details
   */
  @GetMapping("/games/{gameId}/winner")
  public ResponseEntity<WinnerResponse> getWinner(
      @PathVariable Long gameId,
      Principal principal) {
    GameResultDto results = completionService.getGameResults(gameId);

    if (results.winnerId() == null) {
      return ResponseEntity.notFound().build();
    }

    WinnerResponse winner = new WinnerResponse(
        results.winnerId(),
        results.winnerName(),
        results.teamResults().stream()
            .filter(r -> r.isWinner())
            .findFirst()
            .map(r -> r.score())
            .orElse(0),
        results.durationMinutes()
    );

    return ResponseEntity.ok(winner);
  }

  /**
   * Response DTO for winner announcement.
   */
  public record WinnerResponse(
      Long teamId,
      String teamName,
      int finalScore,
      long gameDurationMinutes
  ) {
  }
}
