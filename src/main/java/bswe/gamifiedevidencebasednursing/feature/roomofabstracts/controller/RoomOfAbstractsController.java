package bswe.gamifiedevidencebasednursing.feature.roomofabstracts.controller;

import bswe.gamifiedevidencebasednursing.feature.roomofabstracts.dto.request.VerifyRoomOfAbstractsAnswersDto;
import bswe.gamifiedevidencebasednursing.feature.roomofabstracts.dto.response.ResultDto;
import bswe.gamifiedevidencebasednursing.feature.roomofabstracts.service.RoomOfAbstractsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rooms/roomofabstracts")
public class RoomOfAbstractsController {

  private final RoomOfAbstractsService roomOfAbstractsService;

  public RoomOfAbstractsController(RoomOfAbstractsService roomOfAbstractsService) {
    this.roomOfAbstractsService = roomOfAbstractsService;
  }

  @PostMapping(value = "/verify")
  public ResponseEntity<ResultDto> verifyRoomOfAbstractsAnswers(@RequestBody VerifyRoomOfAbstractsAnswersDto verifyRoomOfAbstractsAnswersDto) {
    ResultDto result = roomOfAbstractsService.verifyRoomOfAbstractsAnswers(verifyRoomOfAbstractsAnswersDto);
    return ResponseEntity.ok(result);
  }
}
