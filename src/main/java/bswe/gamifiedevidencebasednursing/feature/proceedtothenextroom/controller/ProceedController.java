package bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.controller;

import bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.dto.request.ProceedDto;
import bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.dto.response.RoomOfAbstractsResponseDto;
import bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.service.ProceedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game/proceed")
public class ProceedController {

  private final ProceedService proceedService;

  public ProceedController(ProceedService proceedService) {
    this.proceedService = proceedService;
  }

  @PostMapping
  public ResponseEntity<RoomOfAbstractsResponseDto> proceedToTheNextRoom(
      @RequestBody ProceedDto proceedDto) {
    return proceedService.proceedToTheNextRoom(proceedDto);
  }
}
