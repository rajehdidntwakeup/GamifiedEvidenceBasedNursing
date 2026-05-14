package bswe.gamifiedevidencebasednursing.feature.admindashboard.service;

import java.util.List;

import bswe.gamifiedevidencebasednursing.domain.Game;
import bswe.gamifiedevidencebasednursing.feature.admindashboard.dto.response.MissionPasswordDto;
import bswe.gamifiedevidencebasednursing.feature.admindashboard.dto.response.SessionPasswordsDto;
import bswe.gamifiedevidencebasednursing.repository.GameRepository;
import bswe.gamifiedevidencebasednursing.repository.TeamRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdminDashboardService {

  private final GameRepository gameRepository;
  private final TeamRepository teamRepository;

  public AdminDashboardService(GameRepository gameRepository, TeamRepository teamRepository) {
    this.gameRepository = gameRepository;
    this.teamRepository = teamRepository;
  }

  public ResponseEntity<SessionPasswordsDto> getSessionPasswords() {
    Game game = gameRepository.findCreatedOrRunningGame().orElse(null);
    if (game == null) {
      throw new ResponseStatusException(HttpStatusCode.valueOf(404), "No running game found");
    }

    List<MissionPasswordDto> missionPasswordDtoList = teamRepository.findAllMissionPasswordsByGameId(game.getId());
    if (missionPasswordDtoList.isEmpty()) {
      throw new ResponseStatusException(HttpStatusCode.valueOf(404), "No missions found");
    }

    return new ResponseEntity<>(new SessionPasswordsDto(game.getId(), missionPasswordDtoList), HttpStatusCode.valueOf(200));
  }
}
