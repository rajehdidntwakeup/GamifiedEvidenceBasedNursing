package bswe.gamifiedevidencebasednursing.service;

import bswe.gamifiedevidencebasednursing.domain.Game;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.domain.dto.LeaderboardEntryDto;
import bswe.gamifiedevidencebasednursing.domain.dto.LeaderboardFilterDto;
import bswe.gamifiedevidencebasednursing.domain.enums.Status;
import bswe.gamifiedevidencebasednursing.repository.GameRepository;
import bswe.gamifiedevidencebasednursing.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Service for leaderboard operations.
 * Manages rankings, scores, and live updates.
 */
@Service
public class LeaderboardService {

  private final GameRepository gameRepository;
  private final TeamRepository teamRepository;

  // In-memory cache for live rankings (gameId -> sorted leaderboard)
  private final Map<Long, List<LeaderboardEntryDto>> leaderboardCache = new ConcurrentHashMap<>();

  public LeaderboardService(GameRepository gameRepository, TeamRepository teamRepository) {
    this.gameRepository = gameRepository;
    this.teamRepository = teamRepository;
  }

  /**
   * Get live leaderboard for a game.
   *
   * @param gameId the game ID
   * @return sorted leaderboard
   */
  @Transactional(readOnly = true)
  public List<LeaderboardEntryDto> getGameLeaderboard(Long gameId) {
    // Check cache first
    List<LeaderboardEntryDto> cached = leaderboardCache.get(gameId);
    if (cached != null && isCacheValid(gameId)) {
      return cached;
    }

    Game game = gameRepository.findById(gameId)
        .orElseThrow(() -> new IllegalArgumentException("Game not found"));

    List<LeaderboardEntryDto> leaderboard = buildLeaderboard(game);
    leaderboardCache.put(gameId, leaderboard);

    return leaderboard;
  }

  /**
   * Get team position in leaderboard.
   *
   * @param gameId the game ID
   * @param teamId the team ID
   * @return team's leaderboard entry
   */
  @Transactional(readOnly = true)
  public LeaderboardEntryDto getTeamPosition(Long gameId, Long teamId) {
    List<LeaderboardEntryDto> leaderboard = getGameLeaderboard(gameId);
    return leaderboard.stream()
        .filter(e -> e.teamId().equals(teamId))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Team not found in leaderboard"));
  }

  /**
   * Get top N teams.
   *
   * @param gameId the game ID
   * @param limit  number of entries
   * @return top teams
   */
  @Transactional(readOnly = true)
  public List<LeaderboardEntryDto> getTopTeams(Long gameId, int limit) {
    List<LeaderboardEntryDto> leaderboard = getGameLeaderboard(gameId);
    return leaderboard.stream()
        .limit(limit)
        .collect(Collectors.toList());
  }

  /**
   * Get global leaderboard across all games.
   *
   * @return global rankings
   */
  @Transactional(readOnly = true)
  public List<LeaderboardEntryDto> getGlobalLeaderboard() {
    List<Game> games = gameRepository.findAll();
    List<LeaderboardEntryDto> allEntries = new ArrayList<>();

    for (Game game : games) {
      allEntries.addAll(buildLeaderboard(game));
    }

    List<LeaderboardEntryDto> sorted = allEntries.stream()
        .sorted(Comparator.comparingInt(LeaderboardEntryDto::score).reversed())
        .collect(Collectors.toList());
    
    // Re-rank with index
    List<LeaderboardEntryDto> result = new ArrayList<>();
    for (int i = 0; i < sorted.size(); i++) {
        LeaderboardEntryDto entry = sorted.get(i);
        result.add(new LeaderboardEntryDto(
            entry.teamId(),
            entry.teamName(),
            entry.mission(),
            i + 1, // Re-rank globally
            entry.score(),
            entry.totalQuestions(),
            entry.correctAnswers(),
            entry.hintsUsed(),
            entry.completionTimeMinutes(),
            entry.accuracyPercentage(),
            entry.isCurrentUser(),
            entry.status(),
            entry.gameId(),
            entry.gamePassword(),
            entry.lastUpdated()
        ));
    }
    return result;
  }

  /**
   * Get leaderboard filtered by mission.
   *
   * @param missionName the mission name
   * @return mission-specific leaderboard
   */
  @Transactional(readOnly = true)
  public List<LeaderboardEntryDto> getMissionLeaderboard(String missionName) {
    List<Team> teams = teamRepository.findAll().stream()
        .filter(t -> t.getMission().name().equalsIgnoreCase(missionName))
        .collect(Collectors.toList());

    List<LeaderboardEntryDto> sorted = teams.stream()
        .map(this::toLeaderboardEntry)
        .sorted(Comparator.comparingInt(LeaderboardEntryDto::score).reversed())
        .collect(Collectors.toList());
    
    List<LeaderboardEntryDto> result = new ArrayList<>();
    for (int i = 0; i < sorted.size(); i++) {
        LeaderboardEntryDto entry = sorted.get(i);
        result.add(new LeaderboardEntryDto(
            entry.teamId(),
            entry.teamName(),
            entry.mission(),
            i + 1,
            entry.score(),
            entry.totalQuestions(),
            entry.correctAnswers(),
            entry.hintsUsed(),
            entry.completionTimeMinutes(),
            entry.accuracyPercentage(),
            entry.isCurrentUser(),
            entry.status(),
            entry.gameId(),
            entry.gamePassword(),
            entry.lastUpdated()
        ));
    }
    return result;
  }

  /**
   * Get filtered leaderboard.
   *
   * @param filters filter criteria
   * @return filtered results
   */
  @Transactional(readOnly = true)
  public List<LeaderboardEntryDto> getFilteredLeaderboard(LeaderboardFilterDto filters) {
    List<LeaderboardEntryDto> entries;

    if (filters.gameId() != null) {
      entries = getGameLeaderboard(filters.gameId());
    } else {
      entries = getGlobalLeaderboard();
    }

    // Apply filters
    return entries.stream()
        .filter(e -> filters.mission() == null || e.mission().equalsIgnoreCase(filters.mission()))
        .filter(e -> filters.minScore() == null || e.score() >= filters.minScore())
        .filter(e -> filters.maxScore() == null || e.score() <= filters.maxScore())
        .filter(e -> !filters.completedOnly() || e.status() == LeaderboardEntryDto.Status.STABLE)
        .filter(e -> !filters.activeOnly() || e.status() != LeaderboardEntryDto.Status.STABLE)
        .sorted(getComparator(filters.sortBy(), filters.sortOrder()))
        .skip(filters.offset())
        .limit(filters.limit())
        .collect(Collectors.toList());
  }

  /**
   * Get ranking history for a team.
   *
   * @param gameId the game ID
   * @param teamId the team ID
   * @return historical positions
   */
  public List<LeaderboardEntryDto.HistoryEntry> getTeamHistory(Long gameId, Long teamId) {
    // In a real implementation, this would query a history table
    // For now, return mock data
    List<LeaderboardEntryDto.HistoryEntry> history = new ArrayList<>();
    history.add(new LeaderboardEntryDto.HistoryEntry(
        Instant.now().minusSeconds(300), 5, 200, "Joined game"
    ));
    history.add(new LeaderboardEntryDto.HistoryEntry(
        Instant.now().minusSeconds(240), 3, 350, "Answered Q1 correctly"
    ));
    history.add(new LeaderboardEntryDto.HistoryEntry(
        Instant.now().minusSeconds(180), 2, 500, "Answered Q2 correctly"
    ));
    history.add(new LeaderboardEntryDto.HistoryEntry(
        Instant.now().minusSeconds(60), 1, 650, "Answered Q3 correctly"
    ));
    return history;
  }

  /**
   * Get teams around a specific team (neighbors).
   *
   * @param gameId the game ID
   * @param teamId the team ID
   * @param range  number of teams above/below
   * @return neighbor entries
   */
  @Transactional(readOnly = true)
  public List<LeaderboardEntryDto> getTeamNeighbors(Long gameId, Long teamId, int range) {
    List<LeaderboardEntryDto> leaderboard = getGameLeaderboard(gameId);

    int teamIndex = -1;
    for (int i = 0; i < leaderboard.size(); i++) {
      if (leaderboard.get(i).teamId().equals(teamId)) {
        teamIndex = i;
        break;
      }
    }

    if (teamIndex == -1) {
      throw new IllegalArgumentException("Team not found in leaderboard");
    }

    int start = Math.max(0, teamIndex - range);
    int end = Math.min(leaderboard.size(), teamIndex + range + 1);

    return leaderboard.subList(start, end);
  }

  /**
   * Invalidate leaderboard cache for a game.
   *
   * @param gameId the game ID
   */
  public void invalidateCache(Long gameId) {
    leaderboardCache.remove(gameId);
  }

  // Helper methods

  private List<LeaderboardEntryDto> buildLeaderboard(Game game) {
    List<Team> teams = game.getTeamList();
    List<LeaderboardEntryDto> entries = new ArrayList<>();

    for (Team team : teams) {
      entries.add(toLeaderboardEntry(team));
    }

    // Sort by score descending
    entries.sort(Comparator.comparingInt(LeaderboardEntryDto::score).reversed());

    // Re-assign ranks
    List<LeaderboardEntryDto> result = new ArrayList<>();
    for (int i = 0; i < entries.size(); i++) {
        LeaderboardEntryDto entry = entries.get(i);
        result.add(new LeaderboardEntryDto(
            entry.teamId(),
            entry.teamName(),
            entry.mission(),
            i + 1,
            entry.score(),
            entry.totalQuestions(),
            entry.correctAnswers(),
            entry.hintsUsed(),
            entry.completionTimeMinutes(),
            entry.accuracyPercentage(),
            entry.isCurrentUser(),
            entry.status(),
            entry.gameId(),
            entry.gamePassword(),
            entry.lastUpdated()
        ));
    }
    return result;
  }

  private LeaderboardEntryDto toLeaderboardEntry(Team team) {
    // Calculate stats
    int score = calculateScore(team);
    int totalQuestions = 10; // TODO: Get from rooms
    int correctAnswers = 8;  // TODO: Track correct answers
    int hintsUsed = 2;       // TODO: Track hints
    int completionTime = calculateCompletionTime(team);
    double accuracy = totalQuestions > 0 ? (correctAnswers * 100.0 / totalQuestions) : 0;

    // Determine status based on previous position (simplified)
    LeaderboardEntryDto.Status status = LeaderboardEntryDto.Status.STABLE;

    return new LeaderboardEntryDto(
        team.getId(),
        team.getMission().name(), // Using mission as team name for now
        team.getMission().name(),
        0, // Rank assigned later
        score,
        totalQuestions,
        correctAnswers,
        hintsUsed,
        completionTime,
        accuracy,
        false,
        status,
        team.getGame().getId(),
        team.getGame().getPassword(),
        Instant.now()
    );
  }

  private int calculateScore(Team team) {
    // TODO: Implement proper score calculation
    // For now, return a placeholder based on mission
    return team.getMission().ordinal() * 100;
  }

  private int calculateCompletionTime(Team team) {
    if (team.getGame().getFinish() == null) {
      return (int) Duration.between(team.getGame().getBegin(), Instant.now()).toMinutes();
    }
    return (int) Duration.between(team.getGame().getBegin(), team.getGame().getFinish()).toMinutes();
  }

  private boolean isCacheValid(Long gameId) {
    // In a real implementation, check cache timestamp
    // For now, always consider valid for 30 seconds
    return true;
  }

  private Comparator<LeaderboardEntryDto> getComparator(
      LeaderboardFilterDto.SortBy sortBy,
      LeaderboardFilterDto.SortOrder order) {

    Comparator<LeaderboardEntryDto> comparator = switch (sortBy) {
      case SCORE -> Comparator.comparingInt(LeaderboardEntryDto::score);
      case RANK -> Comparator.comparingInt(LeaderboardEntryDto::rank);
      case COMPLETION_TIME -> Comparator.comparingInt(LeaderboardEntryDto::completionTimeMinutes);
      case ACCURACY -> Comparator.comparingDouble(LeaderboardEntryDto::accuracyPercentage);
      case LAST_UPDATED -> Comparator.comparing(LeaderboardEntryDto::lastUpdated);
    };

    return order == LeaderboardFilterDto.SortOrder.DESCENDING
        ? comparator.reversed()
        : comparator;
  }
}
