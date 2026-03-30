package bswe.gamifiedevidencebasednursing.landingpage.service;

import bswe.gamifiedevidencebasednursing.repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class LandingPageService {

  private final GameRepository gameRepository;

  public LandingPageService(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  public boolean isThereAnyGameRunning() {
    return gameRepository.findCreatedOrRunningGame().isPresent();
  }
}
