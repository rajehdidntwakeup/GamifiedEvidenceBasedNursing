package bswe.gamifiedevidencebasednursing.domain.dto;

/**
 * DTO for team data.
 */
public record TeamDto(
    Long id,
    String mission,
    String status,
    String location,
    boolean isWinner,
    Long gameId
) {
}
