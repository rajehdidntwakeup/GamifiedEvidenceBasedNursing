package bswe.gamifiedevidencebasednursing.feature.gamecreation.dto.request;

import jakarta.validation.constraints.NotNull;

/**
 * Request DTO for creating a new game.
 */
public record CreateGameRequest(
    @NotNull String password
) {
}
