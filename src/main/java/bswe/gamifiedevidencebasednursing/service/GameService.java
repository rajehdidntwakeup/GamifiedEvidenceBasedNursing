package bswe.gamifiedevidencebasednursing.service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import bswe.gamifiedevidencebasednursing.domain.dto.response.GameResponseDto;
import bswe.gamifiedevidencebasednursing.domain.Game;
import bswe.gamifiedevidencebasednursing.domain.enums.Mission;
import bswe.gamifiedevidencebasednursing.repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService {

  private final GameRepository gameRepository;
  private final TeamService teamService;

  public GameService(GameRepository gameRepository, TeamService teamService) {
    this.gameRepository = gameRepository;
    this.teamService = teamService;
  }

  public GameResponseDto createGame(String password) {
    Instant start = Instant.now();
    Game game = new Game(password, start, null);
    game = gameRepository.save(game);
    long teamId1 = teamService.createTeam(game, Mission.WOUND_CARE_FOR_PRESSURE_ULCERS);
    long teamId2 = teamService.createTeam(game, Mission.FALL_PREVENTION_IN_GERIATRICS);
    long teamId3 = teamService.createTeam(game, Mission.PAIN_MANAGEMENT_IN_POSTOPERATIVE_CARE);
    long teamId4 = teamService.createTeam(game, Mission.NUTRITIONAL_INTERVENTIONS_FOR_MALNUTRITION);
    long teamId5 = teamService.createTeam(game, Mission.PREVENTION_OF_CATHETER_ASSOCIATED_URINARY_TRACT_INFECTIONS);
    GameResponseDto gameResponseDto = new GameResponseDto(game.getId());
    Map<Mission, Long> teamMissions = new HashMap<>();
    teamMissions.put(Mission.WOUND_CARE_FOR_PRESSURE_ULCERS, teamId1);
    teamMissions.put(Mission.FALL_PREVENTION_IN_GERIATRICS, teamId2);
    teamMissions.put(Mission.PAIN_MANAGEMENT_IN_POSTOPERATIVE_CARE, teamId3);
    teamMissions.put(Mission.NUTRITIONAL_INTERVENTIONS_FOR_MALNUTRITION, teamId4);
    teamMissions.put(Mission.PREVENTION_OF_CATHETER_ASSOCIATED_URINARY_TRACT_INFECTIONS, teamId5);
    gameResponseDto.setTeamMissions(teamMissions);
    return gameResponseDto;
  }
}
