package bswe.gamifiedevidencebasednursing.service;

import bswe.gamifiedevidencebasednursing.domain.Game;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.domain.enums.Location;
import bswe.gamifiedevidencebasednursing.domain.enums.Mission;
import bswe.gamifiedevidencebasednursing.domain.enums.Status;
import bswe.gamifiedevidencebasednursing.repository.TeamRepository;
import bswe.gamifiedevidencebasednursing.websocket.dto.TeamProgressUpdate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamService {

  private final TeamRepository teamRepository;
  private final RoomService roomService;

  public TeamService(TeamRepository teamRepository, RoomService roomService) {
    this.teamRepository = teamRepository;
    this.roomService = roomService;
  }


  public Long createTeam(Game game, Mission mission) {
    Team team = new Team(Status.READY, Location.START, mission, false);
    team.setGame(game);
    team = teamRepository.save(team);
    roomService.createRoomOfKnowledge(team);
    if (team.getId() == null) {
      throw new IllegalStateException("Failed to create team");
    }
    return team.getId();
  }

  /**
   * Get team progress for WebSocket updates.
   *
   * @param teamId the team ID
   * @return optional containing team progress
   */
  public Optional<TeamProgressUpdate> getTeamProgress(Long teamId) {
    return teamRepository.findById(teamId)
        .map(team -> new TeamProgressUpdate(
            team.getId(),
            team.getMission().name(),
            team.getLocation(),
            team.getStatus(),
            calculateScore(team),
            roomService.getRoomTimer(team),
            getCurrentQuestion(team),
            team.isWinner(),
            null
        ));
  }

  private Integer calculateScore(Team team) {
    // TODO: Implement actual score calculation based on answered questions
    return 0;
  }

  private String getCurrentQuestion(Team team) {
    // TODO: Implement getting current question from team's current room
    return null;
  }
}
