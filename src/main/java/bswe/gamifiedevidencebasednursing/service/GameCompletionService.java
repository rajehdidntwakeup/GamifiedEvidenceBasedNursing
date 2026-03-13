package bswe.gamifiedevidencebasednursing.service;

import bswe.gamifiedevidencebasednursing.domain.Game;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.domain.dto.GameCompletionDto;
import bswe.gamifiedevidencebasednursing.domain.dto.GameResultDto;
import bswe.gamifiedevidencebasednursing.domain.dto.LeaderboardEntryDto;
import bswe.gamifiedevidencebasednursing.domain.enums.Location;
import bswe.gamifiedevidencebasednursing.domain.enums.Status;
import bswe.gamifiedevidencebasednursing.repository.GameRepository;
import bswe.gamifiedevidencebasednursing.repository.TeamRepository;
import bswe.gamifiedevidencebasednursing.websocket.dto.AnswerSubmittedEvent;
import bswe.gamifiedevidencebasednursing.websocket.service.WebSocketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Service for game completion logic.
 * Handles winner detection, final scoring, and completion notifications.
 */
@Service
public class GameCompletionService {

  private final GameRepository gameRepository;
  private final TeamRepository teamRepository;
  private final WebSocketService webSocketService;
  private final LeaderboardService leaderboardService;

  public GameCompletionService(GameRepository gameRepository,
                               TeamRepository teamRepository,
                               WebSocketService webSocketService,
                               LeaderboardService leaderboardService) {
    this.gameRepository = gameRepository;
    this.teamRepository = teamRepository;
    this.webSocketService = webSocketService;
    this.leaderboardService = leaderboardService;
  }

  /**
   * Check if a team has completed all rooms.
   *
   * @param teamId the team ID
   * @return completion status
   */
  @Transactional(readOnly = true)
  public boolean isTeamComplete(Long teamId) {
    Team team = teamRepository.findById(teamId)
        .orElseThrow(() -> new IllegalArgumentException("Team not found"));

    // Check if all rooms are completed
    return team.getRoomList().stream()
        .allMatch(room -> room.getStatus() == Status.FINISHED);
  }

  /**
   * Complete a team's game session.
   *
   * @param teamId the team ID
   * @return completion details
   */
  @Transactional
  public GameCompletionDto completeTeamGame(Long teamId) {
    Team team = teamRepository.findById(teamId)
        .orElseThrow(() -> new IllegalArgumentException("Team not found"));

    if (team.getStatus() == Status.FINISHED) {
      throw new IllegalStateException("Team has already completed the game");
    }

    // Update team status
    team.setStatus(Status.FINISHED);
    teamRepository.save(team);

    // Calculate final score
    int finalScore = calculateFinalScore(team);

    // Invalidate leaderboard cache
    leaderboardService.invalidateCache(team.getGame().getId());

    // Broadcast completion
    broadcastTeamCompletion(team, finalScore);

    // Check if all teams are done
    checkGameCompletion(team.getGame().getId());

    return new GameCompletionDto(
        team.getId(),
        team.getMission().name(),
        finalScore,
        calculateCompletionTime(team),
        team.getGame().getId(),
        Instant.now(),
        getRankingPosition(team.getGame().getId(), teamId)
    );
  }

  /**
   * Check and handle game completion when all teams finish.
   *
   * @param gameId the game ID
   * @return game result if completed
   */
  @Transactional
  public Optional<GameResultDto> checkGameCompletion(Long gameId) {
    Game game = gameRepository.findById(gameId)
        .orElseThrow(() -> new IllegalArgumentException("Game not found"));

    List<Team> teams = game.getTeamList();
    long finishedTeams = teams.stream()
        .filter(t -> t.getStatus() == Status.FINISHED)
        .count();

    // If all teams finished, end the game
    if (finishedTeams == teams.size()) {
      return Optional.of(endGame(game));
    }

    return Optional.empty();
  }

  /**
   * End a game and determine winner.
   *
   * @param game the game to end
   * @return final game result
   */
  @Transactional
  public GameResultDto endGame(Game game) {
    game.setFinish(Instant.now());

    // Determine winner
    Team winner = game.getTeamList().stream()
        .max(Comparator.comparingInt(this::calculateFinalScore))
        .orElse(null);

    if (winner != null) {
      winner.setWinner(true);
      teamRepository.save(winner);
    }

    gameRepository.save(game);

    // Build results
    List<GameResultDto.TeamResultDto> teamResults = new ArrayList<>();
    for (Team team : game.getTeamList()) {
      teamResults.add(new GameResultDto.TeamResultDto(
          team.getId(),
          team.getMission().name(),
          calculateFinalScore(team),
          calculateCompletionTime(team),
          team.isWinner(),
          getRankingPosition(game.getId(), team.getId())
      ));
    }

    // Sort by score descending
    teamResults.sort(Comparator.comparingInt(GameResultDto.TeamResultDto::score).reversed());

    GameResultDto result = new GameResultDto(
        game.getId(),
        game.getBegin(),
        game.getFinish(),
        Duration.between(game.getBegin(), game.getFinish()).toMinutes(),
        winner != null ? winner.getId() : null,
        winner != null ? winner.getMission().name() : null,
        teamResults
    );

    // Broadcast game completion
    broadcastGameCompletion(game, result);

    return result;
  }

  /**
   * Force end a game (admin only).
   *
   * @param gameId the game ID
   * @return game result
   */
  @Transactional
  public GameResultDto forceEndGame(Long gameId) {
    Game game = gameRepository.findById(gameId)
        .orElseThrow(() -> new IllegalArgumentException("Game not found"));

    // Mark all unfinished teams as completed
    for (Team team : game.getTeamList()) {
      if (team.getStatus() != Status.FINISHED) {
        team.setStatus(Status.FINISHED);
        teamRepository.save(team);
      }
    }

    return endGame(game);
  }

  /**
   * Get final results for a completed game.
   *
   * @param gameId the game ID
   * @return game results
   */
  @Transactional(readOnly = true)
  public GameResultDto getGameResults(Long gameId) {
    Game game = gameRepository.findById(gameId)
        .orElseThrow(() -> new IllegalArgumentException("Game not found"));

    if (game.getFinish() == null) {
      throw new IllegalStateException("Game is not yet completed");
    }

    List<GameResultDto.TeamResultDto> teamResults = new ArrayList<>();
    for (Team team : game.getTeamList()) {
      teamResults.add(new GameResultDto.TeamResultDto(
          team.getId(),
          team.getMission().name(),
          calculateFinalScore(team),
          calculateCompletionTime(team),
          team.isWinner(),
          getRankingPosition(gameId, team.getId())
      ));
    }

    teamResults.sort(Comparator.comparingInt(GameResultDto.TeamResultDto::score).reversed());

    Team winner = game.getTeamList().stream()
        .filter(Team::isWinner)
        .findFirst()
        .orElse(null);

    return new GameResultDto(
        game.getId(),
        game.getBegin(),
        game.getFinish(),
        Duration.between(game.getBegin(), game.getFinish()).toMinutes(),
        winner != null ? winner.getId() : null,
        winner != null ? winner.getMission().name() : null,
        teamResults
    );
  }

  /**
   * Calculate final score for a team.
   *
   * @param team the team
   * @return final score
   */
  public int calculateFinalScore(Team team) {
    int baseScore = 0;
    int timeBonus = 0;
    int hintPenalty = 0;

    // Sum room scores
    for (Room room : team.getRoomList()) {
      baseScore += calculateRoomScore(room);
    }

    // Time bonus for completing quickly
    int completionTime = calculateCompletionTime(team);
    if (completionTime < 30) {
      timeBonus = 500;
    } else if (completionTime < 60) {
      timeBonus = 300;
    } else if (completionTime < 90) {
      timeBonus = 100;
    }

    // Hint penalty
    int hintsUsed = calculateHintsUsed(team);
    hintPenalty = hintsUsed * 50;

    return Math.max(0, baseScore + timeBonus - hintPenalty);
  }

  /**
   * Calculate score for a room.
   *
   * @param room the room
   * @return room score
   */
  private int calculateRoomScore(Room room) {
    // Base score per room
    int baseScore = 1000;

    // Time bonus based on remaining timer
    int timeBonus = room.getTimer() * 2;

    return baseScore + timeBonus;
  }

  /**
   * Calculate completion time in minutes.
   *
   * @param team the team
   * @return minutes taken
   */
  private int calculateCompletionTime(Team team) {
    Instant end = team.getStatus() == Status.FINISHED
        ? Instant.now() // Should track actual completion time
        : Instant.now();
    return (int) Duration.between(team.getGame().getBegin(), end).toMinutes();
  }

  /**
   * Calculate hints used by a team.
   *
   * @param team the team
   * @return number of hints
   */
  private int calculateHintsUsed(Team team) {
    // TODO: Track hints in database
    return 0;
  }

  /**
   * Get team's ranking position.
   *
   * @param gameId the game ID
   * @param teamId the team ID
   * @return ranking position
   */
  private int getRankingPosition(Long gameId, Long teamId) {
    return leaderboardService.getGameLeaderboard(gameId).stream()
        .filter(e -> e.teamId().equals(teamId))
        .findFirst()
        .map(LeaderboardEntryDto::rank)
        .orElse(0);
  }

  /**
   * Broadcast team completion via WebSocket.
   *
   * @param team the completed team
   * @param finalScore the final score
   */
  private void broadcastTeamCompletion(Team team, int finalScore) {
    // Notify all game subscribers
    webSocketService.sendTeamProgressUpdate(
        team.getGame().getId(),
        new bswe.gamifiedevidencebasednursing.websocket.dto.TeamProgressUpdate(
            team.getId(),
            team.getMission().name(),
            team.getLocation(),
            Status.FINISHED,
            finalScore,
            0,
            null,
            team.isWinner(),
            Instant.now()
        )
    );
  }

  /**
   * Broadcast game completion via WebSocket.
   *
   * @param game the completed game
   * @param result the game result
   */
  private void broadcastGameCompletion(Game game, GameResultDto result) {
    // Notify winner
    if (result.winnerId() != null) {
      webSocketService.sendWinnerAnnouncement(
          game.getId(),
          result.winnerId(),
          result.winnerName()
      );
    }

    // Invalidate leaderboard cache
    leaderboardService.invalidateCache(game.getId());
  }
}
