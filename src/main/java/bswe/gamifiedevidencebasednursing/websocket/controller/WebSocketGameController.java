package bswe.gamifiedevidencebasednursing.websocket.controller;

import bswe.gamifiedevidencebasednursing.websocket.dto.TeamJoinEvent;
import bswe.gamifiedevidencebasednursing.websocket.service.WebSocketService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.time.Instant;

/**
 * WebSocket controller for game-related messages.
 * Handles team actions and progress updates via STOMP protocol.
 */
@Controller
public class WebSocketGameController {

  private final WebSocketService webSocketService;

  public WebSocketGameController(WebSocketService webSocketService) {
    this.webSocketService = webSocketService;
  }

  /**
   * Handle team join message from client.
   *
   * @param gameId the game ID
   * @param teamId the team ID joining
   * @param accessor message headers
   * @param principal authenticated user
   */
  @MessageMapping("/game/{gameId}/join")
  public void handleTeamJoin(
      @DestinationVariable Long gameId,
      @Payload Long teamId,
      SimpMessageHeaderAccessor accessor,
      Principal principal) {

    String username = principal != null ? principal.getName() : "anonymous";

    // Broadcast team join to all game subscribers
    TeamJoinEvent joinEvent = new TeamJoinEvent(
        teamId,
        accessor.getFirstNativeHeader("teamName"),
        gameId,
        username,
        Instant.now()
    );

    webSocketService.sendTeamJoinEvent(gameId, joinEvent);
  }

  /**
   * Handle heartbeat/ping from client to keep connection alive.
   *
   * @param gameId the game ID
   * @param teamId the team ID
   */
  @MessageMapping("/game/{gameId}/team/{teamId}/ping")
  public void handleHeartbeat(
      @DestinationVariable Long gameId,
      @DestinationVariable Long teamId) {
    // No-op - just keeps connection alive
    // Could log activity if needed
  }

  /**
   * Handle request for current game state.
   *
   * @param gameId the game ID
   * @param teamId the requesting team ID
   */
  @MessageMapping("/game/{gameId}/team/{teamId}/sync")
  public void handleSyncRequest(
      @DestinationVariable Long gameId,
      @DestinationVariable Long teamId) {
    // Fetch current team progress and send update
    /*
    teamService.getTeamProgress(teamId)
        .ifPresent(progress -> webSocketService.sendTeamProgressUpdateToTeam(teamId, progress));

     */
  }
}
