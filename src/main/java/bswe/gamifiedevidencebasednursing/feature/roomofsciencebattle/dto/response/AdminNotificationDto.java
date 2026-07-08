package bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.dto.response;

import java.time.Instant;
import java.util.List;


public record AdminNotificationDto(
    Long missionId,
    String missionName,
    Long roomId,
    String roomName,
    Instant submittedAt,
    List<AnswerDetailDto> answers
) {
  public AdminNotificationDto {
    answers = answers == null ? null : List.copyOf(answers);
  }

  @Override
  public List<AnswerDetailDto> answers() {
    return answers == null ? null : List.copyOf(answers);
  }
}
