package bswe.gamifiedevidencebasednursing.landingpage.controller;

import bswe.gamifiedevidencebasednursing.landingpage.service.LandingPageService;
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

}
