package bswe.gamifiedevidencebasednursing.websocket.service;

import bswe.gamifiedevidencebasednursing.websocket.dto.AnswerSubmittedEvent;
import bswe.gamifiedevidencebasednursing.websocket.dto.TeamJoinEvent;
import bswe.gamifiedevidencebasednursing.websocket.dto.TeamProgressUpdate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Service for sending WebSocket messages to clients.
 * Handles team progress updates, join events, and answer submissions.
 */
@Service
public class WebSocketService {

  private final SimpMessagingTemplate messagingTemplate;

  public WebSocketService(SimpMessagingTemplate messagingTemplate) {
    this.messagingTemplate = messagingTemplate;
  }

  /**
   * Send team progress update to all subscribers of a game.
   *
   * @param gameId the game ID
   * @param update the progress update
   */
  public void sendTeamProgressUpdate(Long gameId, TeamProgressUpdate update) {
    messagingTemplate.convertAndSend("/topic/game/" + gameId + "/progress", update);
  }

  /**
   * Send team progress update to a specific team.
   *
   * @param teamId the team ID
   * @param update the progress update
   */
  public void sendTeamProgressUpdateToTeam(Long teamId, TeamProgressUpdate update) {
    messagingTemplate.convertAndSend("/topic/team/" + teamId + "/progress", update);
  }

  /**
   * Notify all subscribers when a team joins a game.
   *
   * @param gameId the game ID
   * @param event the join event
   */
  public void sendTeamJoinEvent(Long gameId, TeamJoinEvent event) {
    messagingTemplate.convertAndSend("/topic/game/" + gameId + "/joins", event);
  }

  /**
   * Send answer submission event to game subscribers.
   *
   * @param gameId the game ID
   * @param event the answer event
   */
  public void sendAnswerSubmittedEvent(Long gameId, AnswerSubmittedEvent event) {
    messagingTemplate.convertAndSend("/topic/game/" + gameId + "/answers", event);
  }

  /**
   * Send answer result to specific team.
   *
   * @param teamId the team ID
   * @param event the answer event
   */
  public void sendAnswerResultToTeam(Long teamId, AnswerSubmittedEvent event) {
    messagingTemplate.convertAndSend("/topic/team/" + teamId + "/answer-result", event);
  }

  /**
   * Send timer update to a specific team.
   *
   * @param teamId the team ID
   * @param secondsRemaining seconds remaining
   */
  public void sendTimerUpdate(Long teamId, int secondsRemaining) {
    messagingTemplate.convertAndSend("/topic/team/" + teamId + "/timer", secondsRemaining);
  }

  /**
   * Notify game subscribers of a winner.
   *
   * @param gameId the game ID
   * @param teamId the winning team ID
   * @param teamName the winning team name
   */
  public void sendWinnerAnnouncement(Long gameId, Long teamId, String teamName) {
    messagingTemplate.convertAndSend("/topic/game/" + gameId + "/winner",
        new WinnerAnnouncement(teamId, teamName));
  }

  /**
   * Inner class for winner announcements.
   */
  public record WinnerAnnouncement(Long teamId, String teamName) {
  }
}
