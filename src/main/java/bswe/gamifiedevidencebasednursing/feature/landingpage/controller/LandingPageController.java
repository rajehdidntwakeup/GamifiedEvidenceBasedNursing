package bswe.gamifiedevidencebasednursing.feature.landingpage.controller;

import java.util.List;

import bswe.gamifiedevidencebasednursing.feature.landingpage.dto.response.LandingPageResponse;
import bswe.gamifiedevidencebasednursing.feature.landingpage.service.LandingPageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game/landing")
public class LandingPageController {


  private final LandingPageService landingPageService;

  public LandingPageController(LandingPageService landingPageService) {
    this.landingPageService = landingPageService;
  }

  @GetMapping
  public boolean isGameRunning() {
    return landingPageService.isThereAnyGameRunning();
  }

  @GetMapping("/missions")
  public ResponseEntity<LandingPageResponse> showMissions() {
    return landingPageService.showMissions();
  }



}
