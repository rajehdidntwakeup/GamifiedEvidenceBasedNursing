package bswe.gamifiedevidencebasednursing.domain.dto.request;

import jakarta.validation.constraints.NotNull;

/**
 * Request DTO for submitting an answer.
 */
public record SubmitAnswerRequest(
    @NotNull Long teamId,
    @NotNull Long roomId,
    @NotNull Long questionId,
    @NotNull Long answerId
) {
}
