package bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.controller;

import bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.dto.request.SubmissionDto;
import bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.dto.response.ResultDto;
import bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.dto.response.SubmissionResponseDto;
import bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.service.RoomOfScienceBattleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rooms/roomofsciencebattle")
public class RoomOfScienceBattleController {


  private final RoomOfScienceBattleService roomOfScienceBattleService;

  public RoomOfScienceBattleController(RoomOfScienceBattleService roomOfScienceBattleService) {
    this.roomOfScienceBattleService = roomOfScienceBattleService;
  }

  @PostMapping(value = "/submit")
  public ResponseEntity<SubmissionResponseDto> submitScienceBattle(@RequestBody SubmissionDto submissionDto) {
    return roomOfScienceBattleService.submitScienceBattleEvidence(submissionDto);
  }

  @GetMapping(value = "/results")
  public ResponseEntity<ResultDto> getResults(@RequestParam long roomId, @RequestParam long missionId) {
    return roomOfScienceBattleService.getResults(roomId, missionId);
  }
}
