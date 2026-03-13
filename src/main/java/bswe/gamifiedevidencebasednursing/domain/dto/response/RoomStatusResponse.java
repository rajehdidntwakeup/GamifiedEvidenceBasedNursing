package bswe.gamifiedevidencebasednursing.domain.dto.response;

import bswe.gamifiedevidencebasednursing.domain.dto.QuestionDto;
import bswe.gamifiedevidencebasednursing.domain.enums.Location;
import bswe.gamifiedevidencebasednursing.domain.enums.Status;

import java.util.List;

/**
 * Response DTO for room status and questions.
 */
public record RoomStatusResponse(
    Long roomId,
    Location location,
    Status status,
    int timerRemaining,
    int totalQuestions,
    int answeredQuestions,
    List<QuestionDto> questions,
    boolean isComplete
) {
}
