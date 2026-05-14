package bswe.gamifiedevidencebasednursing.feature.adminfeedbacksubmission.controller;

import bswe.gamifiedevidencebasednursing.feature.adminfeedbacksubmission.dto.request.AnalyticsSubmissionFeedbackDto;
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
                                                            AnalyticsSubmissionFeedbackDto analyticsSubmissionFeedbackDto) {
    return submissionService.analyticsSubmissionFeedback(analyticsSubmissionFeedbackDto);
  }

}
