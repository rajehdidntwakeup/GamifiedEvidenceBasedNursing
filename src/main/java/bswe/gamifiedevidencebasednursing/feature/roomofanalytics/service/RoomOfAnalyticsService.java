package bswe.gamifiedevidencebasednursing.feature.roomofanalytics.service;

import bswe.gamifiedevidencebasednursing.shared.submission.dto.request.SubmissionDto;
import bswe.gamifiedevidencebasednursing.shared.submission.dto.response.ResultDto;
import bswe.gamifiedevidencebasednursing.shared.submission.dto.response.SubmissionResponseDto;
import bswe.gamifiedevidencebasednursing.shared.submission.service.OpenQuestionSubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoomOfAnalyticsService {

  private static final int ANALYTICS_POINTS = 20;
  private final OpenQuestionSubmissionService submissionService;
  private final AnalyticsNotificationService analyticsNotificationService;

  public RoomOfAnalyticsService(OpenQuestionSubmissionService submissionService,
                                AnalyticsNotificationService analyticsNotificationService) {
    this.submissionService = submissionService;
    this.analyticsNotificationService = analyticsNotificationService;
  }

  public ResponseEntity<SubmissionResponseDto> submitAnalytics(SubmissionDto submissionDto) {
    SubmissionResponseDto response = submissionService.processSubmission(
        submissionDto,
        ANALYTICS_POINTS,
        analyticsNotificationService::notifyAdmin
    );
    return ResponseEntity.ok(response);
  }

  public ResponseEntity<ResultDto> getResults(long roomId, long missionId) {
    return ResponseEntity.ok(submissionService.getResults(roomId, missionId));
  }
}
