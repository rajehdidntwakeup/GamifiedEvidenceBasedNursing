package bswe.gamifiedevidencebasednursing.domain.dto.request;

import jakarta.validation.constraints.NotNull;

/**
 * Request DTO for creating a new game.
 */
public record CreateGameRequest(
    @NotNull String password
) {
}
