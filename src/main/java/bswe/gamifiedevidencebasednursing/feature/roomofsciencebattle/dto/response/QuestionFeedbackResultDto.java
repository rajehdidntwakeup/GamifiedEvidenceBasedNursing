package bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.dto.response;

public record QuestionFeedbackResultDto(
    Long questionId,
    boolean approved,
    String answerText
) { }
