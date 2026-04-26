package bswe.gamifiedevidencebasednursing.feature.roomtime.controller;

import bswe.gamifiedevidencebasednursing.feature.roomtime.dto.RoomTimeResponse;
import bswe.gamifiedevidencebasednursing.feature.roomtime.service.RoomTimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rooms/howmuchtimedowehave")
public class RoomTimeController {

  private final RoomTimeService roomTimeService;

  public RoomTimeController(RoomTimeService roomTimeService) {
    this.roomTimeService = roomTimeService;
  }

  @GetMapping
  public ResponseEntity<RoomTimeResponse> getHowMuchTimedowehave(@RequestParam long roomId) {
    return ResponseEntity.ok(roomTimeService.howMuchTimeDoWeHave(roomId));
  }
}
