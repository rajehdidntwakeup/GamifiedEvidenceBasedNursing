package bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.dto.response;

import java.time.Instant;
import java.util.List;



public record ScienceBattleFeedbackDto(
    Long roomId,
    int progress,
    String missionName,
    Instant feedbackAt,
    List<QuestionFeedbackResultDto> questions
) {
  public ScienceBattleFeedbackDto {
    questions = questions == null ? null : List.copyOf(questions);
  }

  @Override
  public List<QuestionFeedbackResultDto> questions() {
    return questions == null ? null : List.copyOf(questions);
  }
}
