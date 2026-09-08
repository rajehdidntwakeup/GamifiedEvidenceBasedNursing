package bswe.gamifiedevidencebasednursing.shared.submission.dto.response;

public record QuestionFeedbackResultDto(
    Long questionId,
    boolean approved,
    String answerText
) { }
