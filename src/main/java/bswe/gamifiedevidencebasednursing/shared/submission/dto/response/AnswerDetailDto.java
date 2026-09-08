package bswe.gamifiedevidencebasednursing.shared.submission.dto.response;

public record AnswerDetailDto(
    Long questionId,
    String questionText,
    String answerText
) { }
