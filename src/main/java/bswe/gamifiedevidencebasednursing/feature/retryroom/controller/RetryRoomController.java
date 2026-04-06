package bswe.gamifiedevidencebasednursing.feature.retryroom.controller;

import bswe.gamifiedevidencebasednursing.feature.retryroom.service.RetryRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rooms/retry")
public class RetryRoomController {

  private final RetryRoomService  retryRoomService;

  public RetryRoomController(RetryRoomService retryRoomService) {
    this.retryRoomService = retryRoomService;
  }

  @PutMapping("/knowledge")
  public ResponseEntity<Integer> retryRoomOfKnowledge(@RequestParam long roomId) {
    return retryRoomService.retryRoomOfKnowledge(roomId);
  }


  @PutMapping("/abstracts")
  public ResponseEntity<Integer> retryRoomOfAbstracts(@RequestParam long roomId) {
    return retryRoomService.retryRoomOfAbstracts(roomId);
  }

}
