package bswe.gamifiedevidencebasednursing.feature.roomofknowledge.dto.request;

public class VerifyAnswerDto {
  private long roomId;
  private long questionId;
  private long answerId;

  public VerifyAnswerDto() {
  }

  public VerifyAnswerDto(long roomId, long questionId, long answerId) {
    this.roomId = roomId;
    this.questionId = questionId;
    this.answerId = answerId;
  }

  public long getRoomId() {
    return roomId;
  }

  public void setRoomId(long roomId) {
    this.roomId = roomId;
  }

  public long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(long questionId) {
    this.questionId = questionId;
  }

  public long getAnswerId() {
    return answerId;
  }

  public void setAnswerId(long answerId) {
    this.answerId = answerId;
  }
}
