package bswe.gamifiedevidencebasednursing.shared.submission.dto.response;

public record SubmissionResponseDto(
    int progress,
    boolean levelOfEvidenceApproved
) {
}
