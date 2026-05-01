package bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response;

import java.time.Instant;
import java.util.List;

public record AdminNotificationDto(
    Long missionId,
    String missionName,
    Long roomId,
    String roomName,
    Instant submittedAt,
    List<AnswerDetailDto> answers
) {}
