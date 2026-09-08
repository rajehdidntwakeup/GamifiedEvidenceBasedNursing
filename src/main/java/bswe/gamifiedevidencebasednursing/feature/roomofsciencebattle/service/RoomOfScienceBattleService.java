package bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.service;

import bswe.gamifiedevidencebasednursing.shared.submission.dto.request.SubmissionDto;
import bswe.gamifiedevidencebasednursing.shared.submission.dto.response.ResultDto;
import bswe.gamifiedevidencebasednursing.shared.submission.dto.response.SubmissionResponseDto;
import bswe.gamifiedevidencebasednursing.shared.submission.service.OpenQuestionSubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoomOfScienceBattleService {

  private static final int SCIENCE_BATTLE_POINTS = 10;
  private final OpenQuestionSubmissionService submissionService;
  private final ScienceBattleNotificationService scienceBattleNotificationService;

  public RoomOfScienceBattleService(OpenQuestionSubmissionService submissionService,
                                    ScienceBattleNotificationService scienceBattleNotificationService) {
    this.submissionService = submissionService;
    this.scienceBattleNotificationService = scienceBattleNotificationService;
  }

  public ResponseEntity<SubmissionResponseDto> submitScienceBattleEvidence(SubmissionDto submissionDto) {
    SubmissionResponseDto response = submissionService.processSubmission(
        submissionDto,
        SCIENCE_BATTLE_POINTS,
        scienceBattleNotificationService::notifyAdmin
    );
    return ResponseEntity.ok(response);
  }

  public ResponseEntity<ResultDto> getResults(long roomId, long missionId) {
    return ResponseEntity.ok(submissionService.getResults(roomId, missionId));
  }
}