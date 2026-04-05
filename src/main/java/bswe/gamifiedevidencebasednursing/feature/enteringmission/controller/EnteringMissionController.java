package bswe.gamifiedevidencebasednursing.feature.enteringmission.controller;

import bswe.gamifiedevidencebasednursing.feature.enteringmission.dto.request.EnteringGameRequest;
import bswe.gamifiedevidencebasednursing.feature.enteringmission.dto.response.EnteringGameResponse;
import bswe.gamifiedevidencebasednursing.feature.enteringmission.service.EnteringMissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game/mission")
public class EnteringMissionController {

  private final EnteringMissionService enteringMissionService;

  public EnteringMissionController(EnteringMissionService enteringMissionService) {
    this.enteringMissionService = enteringMissionService;
  }

  @PostMapping("/enter")
  public ResponseEntity<EnteringGameResponse> enterMission(@RequestBody EnteringGameRequest request) {
    return enteringMissionService.enterMission(request);
  }
}
