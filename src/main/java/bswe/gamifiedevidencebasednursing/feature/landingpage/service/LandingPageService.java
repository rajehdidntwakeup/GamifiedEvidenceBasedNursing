package bswe.gamifiedevidencebasednursing.feature.landingpage.service;

import java.util.List;

import bswe.gamifiedevidencebasednursing.domain.Game;
import bswe.gamifiedevidencebasednursing.feature.landingpage.dto.response.LandingPageResponse;
import bswe.gamifiedevidencebasednursing.repository.GameRepository;
import bswe.gamifiedevidencebasednursing.repository.MissionRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LandingPageService {

  private final GameRepository gameRepository;
  private final MissionRepository missionRepository;

  public LandingPageService(GameRepository gameRepository, MissionRepository missionRepository) {
    this.gameRepository = gameRepository;
    this.missionRepository = missionRepository;
  }

  public boolean isThereAnyGameRunning() {
    return gameRepository.findCreatedOrRunningGame().isPresent();
  }

  public ResponseEntity<LandingPageResponse> showMissions() {
    Game game = gameRepository.findCreatedOrRunningGame().orElse(null);
    if (game == null) {
      throw new ResponseStatusException(HttpStatusCode.valueOf(404), "No running game found");
    }
    List<String> missionNames = missionRepository.findAllMissionNames();
    if (missionNames.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    LandingPageResponse response = new LandingPageResponse(game.getId(), missionNames);
    return ResponseEntity.ok(response);
  }
}
