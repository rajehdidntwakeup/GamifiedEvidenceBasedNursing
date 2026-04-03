package bswe.gamifiedevidencebasednursing.feature.roomofabstracts.dto.request;

import java.util.List;

public class VerifyRoomOfAbstractsAnswersDto {
  private long roomId;
  private long missionId;
  private List<QuestionAnswerDto> answers;

  public VerifyRoomOfAbstractsAnswersDto() {
  }

  public VerifyRoomOfAbstractsAnswersDto(long roomId, long missionId, List<QuestionAnswerDto> answers) {
    this.roomId = roomId;
    this.missionId = missionId;
    this.answers = answers;
  }

  public long getRoomId() {
    return roomId;
  }

  public long getMissionId() {
    return missionId;
  }

  public List<QuestionAnswerDto> getAnswers() {
    return answers;
  }

  public void setAnswers(List<QuestionAnswerDto> answers) {
    this.answers = answers;
  }

  public void setRoomId(long roomId) {
    this.roomId = roomId;
  }

  public void setMissionId(long missionId) {
    this.missionId = missionId;
  }

}
