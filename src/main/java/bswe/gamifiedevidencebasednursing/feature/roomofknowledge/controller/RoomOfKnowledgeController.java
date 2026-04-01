package bswe.gamifiedevidencebasednursing.feature.roomofknowledge.controller;

import bswe.gamifiedevidencebasednursing.feature.roomofknowledge.dto.request.VerifyAnswerDto;
import bswe.gamifiedevidencebasednursing.feature.roomofknowledge.dto.response.ResultDto;
import bswe.gamifiedevidencebasednursing.feature.roomofknowledge.service.RoomOfKnowledgeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/rooms/roomofknowledge")
public class RoomOfKnowledgeController {

  private final RoomOfKnowledgeService roomOfKnowledgeService;

  public RoomOfKnowledgeController(RoomOfKnowledgeService roomOfKnowledgeService) {
    this.roomOfKnowledgeService = roomOfKnowledgeService;
  }

  @PutMapping("/isTheAnswerCorrect")
  public ResponseEntity<Boolean> isTheAnswerCorrect(@RequestBody VerifyAnswerDto verifyAnswerDto) {
    return roomOfKnowledgeService.isTheAnswerCorrect(verifyAnswerDto);
  }

  @GetMapping("/getResult")
  public ResponseEntity<ResultDto> getResult(@RequestParam long roomId) {
    return roomOfKnowledgeService.getResult(roomId);
  }
}
