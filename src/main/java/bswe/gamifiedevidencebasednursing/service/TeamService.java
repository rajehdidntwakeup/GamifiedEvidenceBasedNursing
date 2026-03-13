package bswe.gamifiedevidencebasednursing.service;

import bswe.gamifiedevidencebasednursing.domain.Game;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.domain.enums.Location;
import bswe.gamifiedevidencebasednursing.domain.enums.Mission;
import bswe.gamifiedevidencebasednursing.domain.enums.Status;
import bswe.gamifiedevidencebasednursing.repository.TeamRepository;
import org.springframework.stereotype.Service;

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
}
