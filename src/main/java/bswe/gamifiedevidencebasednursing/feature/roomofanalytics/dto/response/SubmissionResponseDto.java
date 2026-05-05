package bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response;

public record SubmissionResponseDto(
    int progress,
    boolean levelOfEvidenceApproved
) {
}
