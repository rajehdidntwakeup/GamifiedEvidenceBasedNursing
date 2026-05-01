package bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response;

import java.time.Instant;
import java.util.List;

public record AnalyticsFeedbackDto(
    Long roomId,
    String missionName,
    Integer progress,
    Instant feedbackAt,
    List<QuestionFeedbackResultDto> questions
) {}
