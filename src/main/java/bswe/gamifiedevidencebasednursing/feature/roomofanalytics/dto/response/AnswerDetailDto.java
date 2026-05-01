package bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response;

public record AnswerDetailDto(
    Long questionId,
    String questionText,
    String answerText
) {}
