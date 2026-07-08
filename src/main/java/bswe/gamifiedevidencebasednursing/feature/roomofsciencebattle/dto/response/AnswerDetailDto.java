package bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.dto.response;

public record AnswerDetailDto(
    Long questionId,
    String questionText,
    String answerText
) { }
