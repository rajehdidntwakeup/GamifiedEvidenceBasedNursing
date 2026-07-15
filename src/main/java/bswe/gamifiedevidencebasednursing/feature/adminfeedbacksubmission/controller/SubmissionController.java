package bswe.gamifiedevidencebasednursing.feature.adminfeedbacksubmission.controller;

import bswe.gamifiedevidencebasednursing.feature.adminfeedbacksubmission.dto.request.SubmissionFeedbackDto;
import bswe.gamifiedevidencebasednursing.feature.adminfeedbacksubmission.service.SubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/submission")
public class SubmissionController {

  private final SubmissionService submissionService;

  public SubmissionController(SubmissionService submissionService) {
    this.submissionService = submissionService;
  }

  @PostMapping(value = "/analytics")
  public ResponseEntity<String> analyticsSubmissionFeedback(@RequestBody
                                                            SubmissionFeedbackDto submissionFeedbackDto) {
    return submissionService.analyticsSubmissionFeedback(submissionFeedbackDto);
  }

  @PostMapping(value = "/sciencebattle")
  public ResponseEntity<String> scienceBattleSubmissionFeedback(@RequestBody
                                                                 SubmissionFeedbackDto submissionFeedbackDto) {
    return submissionService.scienceBattleSubmissionFeedback(submissionFeedbackDto);
  }

}
