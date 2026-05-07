package bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response;

public record QuestionFeedbackResultDto(
    Long questionId,
    boolean approved,
    String answerText
) { }
