package bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response;

import java.time.Instant;
import java.util.List;

public record AnalyticsFeedbackDto(
    Long roomId,
    int progress,
    String missionName,
    Instant feedbackAt,
    List<QuestionFeedbackResultDto> questions
) {
  public AnalyticsFeedbackDto {
    questions = questions == null ? null : List.copyOf(questions);
  }

  @Override
  public List<QuestionFeedbackResultDto> questions() {
    return questions == null ? null : List.copyOf(questions);
  }
}
