package bswe.gamifiedevidencebasednursing.domain.dto.response;

/**
 * Response DTO for answer submission result.
 */
public record SubmitAnswerResponse(
    boolean correct,
    int pointsEarned,
    int newTotalScore,
    String feedback,
    boolean roomCompleted,
    Long nextQuestionId
) {
}
