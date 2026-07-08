package bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.controller;

import bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.request.SubmissionDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rooms/roomofsciencebattle")
public class RoomOfScienceBattleController {

  @PostMapping(value = "/submit")
  public void submitScienceBattle(@RequestBody SubmissionDto submissionDto) {
  }
}
