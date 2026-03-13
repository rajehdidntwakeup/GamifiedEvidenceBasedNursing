package bswe.gamifiedevidencebasednursing.service;

import bswe.gamifiedevidencebasednursing.domain.Game;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.domain.dto.AdminAnalyticsDto;
import bswe.gamifiedevidencebasednursing.domain.dto.AdminAnalyticsDto.MissionStatsDto;
import bswe.gamifiedevidencebasednursing.domain.dto.AdminAnalyticsDto.QuestionDifficultyDto;
import bswe.gamifiedevidencebasednursing.domain.dto.AdminAnalyticsDto.TopTeamDto;
import bswe.gamifiedevidencebasednursing.domain.dto.AdminGameDto;
import bswe.gamifiedevidencebasednursing.domain.dto.AdminGameDto.AdminTeamSummaryDto;
import bswe.gamifiedevidencebasednursing.domain.dto.AdminTeamDto;
import bswe.gamifiedevidencebasednursing.domain.enums.Status;
import bswe.gamifiedevidencebasednursing.repository.GameRepository;
import bswe.gamifiedevidencebasednursing.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service for admin dashboard operations.
 * Handles game management, team monitoring, and analytics.
 */
@Service
public class AdminService {

  private final GameRepository gameRepository;
  private final TeamRepository teamRepository;

  public AdminService(GameRepository gameRepository, TeamRepository teamRepository) {
    this.gameRepository = gameRepository;
    this.teamRepository = teamRepository;
  }

  /**
   * Get all games with summary statistics.
   *
   * @return list of all games
   */
  @Transactional(readOnly = true)
  public List<AdminGameDto> getAllGames() {
    List<Game> games = gameRepository.findAll();
    return games.stream()
        .map(this::toAdminGameDto)
        .sorted(Comparator.comparing(AdminGameDto::beginTime).reversed())
        .collect(Collectors.toList());
  }

  /**
   * Get detailed game information.
   *
   * @param gameId the game ID
   * @return game details
   */
  @Transactional(readOnly = true)
  public AdminGameDto getGameDetails(Long gameId) {
    Game game = gameRepository.findById(gameId)
        .orElseThrow(() -> new IllegalArgumentException("Game not found"));
    return toAdminGameDto(game);
  }

  /**
   * Delete a game and all associated data.
   *
   * @param gameId the game ID
   */
  @Transactional
  public void deleteGame(Long gameId) {
    Game game = gameRepository.findById(gameId)
        .orElseThrow(() -> new IllegalArgumentException("Game not found"));
    gameRepository.delete(game);
  }

  /**
   * Get all teams in a game.
   *
   * @param gameId the game ID
   * @return list of teams
   */
  @Transactional(readOnly = true)
  public List<AdminTeamDto> getGameTeams(Long gameId) {
    Game game = gameRepository.findById(gameId)
        .orElseThrow(() -> new IllegalArgumentException("Game not found"));
    return game.getTeamList().stream()
        .map(this::toAdminTeamDto)
        .collect(Collectors.toList());
  }

  /**
   * Get team details.
   *
   * @param gameId the game ID
   * @param teamId the team ID
   * @return team details
   */
  @Transactional(readOnly = true)
  public AdminTeamDto getTeamDetails(Long gameId, Long teamId) {
    Team team = teamRepository.findById(teamId)
        .orElseThrow(() -> new IllegalArgumentException("Team not found"));
    if (!team.getGame().getId().equals(gameId)) {
      throw new IllegalArgumentException("Team does not belong to this game");
    }
    return toAdminTeamDto(team);
  }

  /**
   * Start a game.
   *
   * @param gameId the game ID
   * @return updated game
   */
  @Transactional
  public AdminGameDto startGame(Long gameId) {
    Game game = gameRepository.findById(gameId)
        .orElseThrow(() -> new IllegalArgumentException("Game not found"));

    // Update all teams to STARTED status
    game.getTeamList().forEach(team -> {
      // team.setStatus(Status.STARTED); // Uncomment when status field exists
    });

    gameRepository.save(game);
    return toAdminGameDto(game);
  }

  /**
   * End a game.
   *
   * @param gameId the game ID
   * @return updated game
   */
  @Transactional
  public AdminGameDto endGame(Long gameId) {
    Game game = gameRepository.findById(gameId)
        .orElseThrow(() -> new IllegalArgumentException("Game not found"));

    game.setFinish(Instant.now());

    // Mark all incomplete teams as finished
    game.getTeamList().forEach(team -> {
      // if (team.getStatus() != Status.FINISHED) {
      //   team.setStatus(Status.FINISHED);
      // }
    });

    // Determine winner
    Team winner = game.getTeamList().stream()
        .max(Comparator.comparingInt(this::calculateScore))
        .orElse(null);

    if (winner != null) {
      // winner.setWinner(true); // Uncomment when field exists
    }

    gameRepository.save(game);
    return toAdminGameDto(game);
  }

  /**
   * Reset team progress.
   *
   * @param gameId the game ID
   * @param teamId the team ID
   * @return reset team
   */
  @Transactional
  public AdminTeamDto resetTeamProgress(Long gameId, Long teamId) {
    Team team = teamRepository.findById(teamId)
        .orElseThrow(() -> new IllegalArgumentException("Team not found"));
    if (!team.getGame().getId().equals(gameId)) {
      throw new IllegalArgumentException("Team does not belong to this game");
    }

    // Reset team progress
    // team.setStatus(Status.READY);
    // team.setScore(0);
    // team.setQuestionsAnswered(0);
    // team.setRoomList(new ArrayList<>());

    teamRepository.save(team);
    return toAdminTeamDto(team);
  }

  /**
   * Get analytics for a specific game.
   *
   * @param gameId the game ID
   * @return game analytics
   */
  @Transactional(readOnly = true)
  public AdminAnalyticsDto getGameAnalytics(Long gameId) {
    Game game = gameRepository.findById(gameId)
        .orElseThrow(() -> new IllegalArgumentException("Game not found"));

    List<Team> teams = game.getTeamList();

    int activeTeams = (int) teams.stream()
        .filter(t -> t.getStatus() == Status.STARTED)
        .count();

    int completedTeams = (int) teams.stream()
        .filter(t -> t.getStatus() == Status.FINISHED)
        .count();

    double avgScore = teams.stream()
        .mapToInt(this::calculateScore)
        .average()
        .orElse(0);

    // Build mission stats
    List<MissionStatsDto> missionStats = teams.stream()
        .collect(Collectors.groupingBy(
            Team::getMission,
            Collectors.summarizingInt(this::calculateScore)
        ))
        .entrySet().stream()
        .map(e -> new MissionStatsDto(
            e.getKey().name(),
            (int) e.getValue().getCount(),
            0, // completions would need tracking
            e.getValue().getAverage(),
            0 // average time would need tracking
        ))
        .collect(Collectors.toList());

    // Build top teams
    List<TopTeamDto> topTeams = teams.stream()
        .sorted(Comparator.comparingInt(this::calculateScore).reversed())
        .limit(10)
        .map(t -> new TopTeamDto(
            t.getId(),
            t.getMission().name(),
            calculateScore(t),
            calculateCompletionTime(t),
            game.getFinish()
        ))
        .collect(Collectors.toList());

    return new AdminAnalyticsDto(
        1, // totalGames
        activeTeams > 0 ? 1 : 0,
        completedTeams > 0 ? 1 : 0,
        teams.size(),
        teams.size(), // totalPlayers (assuming 1 per team for now)
        calculateAverageCompletionTime(teams),
        avgScore,
        teams.isEmpty() ? 0 : (completedTeams * 100.0 / teams.size()),
        missionStats,
        new ArrayList<>(), // questionDifficulty - would need attempt tracking
        new HashMap<>(), // gamesPerDay - just this game
        topTeams,
        game.getBegin(),
        game.getFinish()
    );
  }

  /**
   * Get global analytics across all games.
   *
   * @return global analytics
   */
  @Transactional(readOnly = true)
  public AdminAnalyticsDto getGlobalAnalytics() {
    List<Game> games = gameRepository.findAll();
    List<Team> allTeams = games.stream()
        .flatMap(g -> g.getTeamList().stream())
        .collect(Collectors.toList());

    int activeGames = (int) games.stream()
        .filter(g -> g.getFinish() == null)
        .count();

    int completedGames = games.size() - activeGames;

    double avgScore = allTeams.stream()
        .mapToInt(this::calculateScore)
        .average()
        .orElse(0);

    // Aggregate mission stats across all games
    List<MissionStatsDto> missionStats = allTeams.stream()
        .collect(Collectors.groupingBy(
            Team::getMission,
            Collectors.summarizingInt(this::calculateScore)
        ))
        .entrySet().stream()
        .map(e -> new MissionStatsDto(
            e.getKey().name(),
            (int) e.getValue().getCount(),
            0,
            e.getValue().getAverage(),
            0
        ))
        .collect(Collectors.toList());

    // Global top teams
    List<TopTeamDto> topTeams = allTeams.stream()
        .sorted(Comparator.comparingInt(this::calculateScore).reversed())
        .limit(10)
        .map(t -> new TopTeamDto(
            t.getId(),
            t.getMission().name(),
            calculateScore(t),
            calculateCompletionTime(t),
            t.getGame().getFinish()
        ))
        .collect(Collectors.toList());

    // Games per day (simplified - just recent games)
    Map<String, Integer> gamesPerDay = new HashMap<>();
    games.forEach(g -> {
      String day = g.getBegin().toString().substring(0, 10);
      gamesPerDay.merge(day, 1, Integer::sum);
    });

    return new AdminAnalyticsDto(
        games.size(),
        activeGames,
        completedGames,
        allTeams.size(),
        allTeams.size(),
        calculateAverageCompletionTime(allTeams),
        avgScore,
        allTeams.isEmpty() ? 0 : (completedGames * 100.0 / games.size()),
        missionStats,
        new ArrayList<>(),
        gamesPerDay,
        topTeams,
        games.isEmpty() ? null : games.get(0).getBegin(),
        games.isEmpty() ? null : Instant.now()
    );
  }

  /**
   * Export game results as CSV.
   *
   * @param gameId the game ID
   * @return CSV string
   */
  @Transactional(readOnly = true)
  public String exportGameResults(Long gameId) {
    Game game = gameRepository.findById(gameId)
        .orElseThrow(() -> new IllegalArgumentException("Game not found"));

    StringBuilder csv = new StringBuilder();
    csv.append("Team ID,Mission,Status,Score,Questions Answered,Time Remaining,Completed,Is Winner\n");

    for (Team team : game.getTeamList()) {
      csv.append(String.format("%d,%s,%s,%d,%d,%d,%s,%s\n",
          team.getId(),
          team.getMission(),
          team.getStatus(),
          calculateScore(team),
          0, // questions answered would need tracking
          0, // time remaining
          team.getStatus() == Status.FINISHED,
          team.isWinner()
      ));
    }

    return csv.toString();
  }

  // Helper methods

  private AdminGameDto toAdminGameDto(Game game) {
    List<AdminTeamSummaryDto> teamSummaries = game.getTeamList().stream()
        .map(t -> new AdminTeamSummaryDto(
            t.getId(),
            t.getMission().name(),
            t.getStatus(),
            calculateScore(t)
        ))
        .collect(Collectors.toList());

    Team winner = game.getTeamList().stream()
        .filter(Team::isWinner)
        .findFirst()
        .orElse(null);

    long activeTeams = game.getTeamList().stream()
        .filter(t -> t.getStatus() == Status.STARTED)
        .count();

    long completedTeams = game.getTeamList().stream()
        .filter(t -> t.getStatus() == Status.FINISHED)
        .count();

    return new AdminGameDto(
        game.getId(),
        game.getPassword(),
        game.getBegin(),
        game.getFinish(),
        game.getFinish() != null ? Status.FINISHED : Status.STARTED,
        game.getTeamList().size(),
        (int) activeTeams,
        (int) completedTeams,
        teamSummaries,
        winner != null ? winner.getId() : null,
        winner != null ? winner.getMission().name() : null
    );
  }

  private AdminTeamDto toAdminTeamDto(Team team) {
    return new AdminTeamDto(
        team.getId(),
        team.getMission(),
        team.getStatus(),
        team.getLocation(),
        calculateScore(team),
        0, // questions answered
        0, // questions correct
        0, // hints used
        0, // time remaining
        team.getGame().getBegin(),
        team.getGame().getFinish(),
        team.getGame().getId(),
        team.getGame().getPassword(),
        team.isWinner(),
        new ArrayList<>() // question attempts
    );
  }

  private int calculateScore(Team team) {
    // TODO: Implement actual score calculation
    return 0;
  }

  private int calculateCompletionTime(Team team) {
    if (team.getGame().getFinish() == null) {
      return 0;
    }
    return (int) Duration.between(team.getGame().getBegin(), team.getGame().getFinish()).toMinutes();
  }

  private double calculateAverageCompletionTime(List<Team> teams) {
    return teams.stream()
        .mapToInt(this::calculateCompletionTime)
        .average()
        .orElse(0);
  }
}
