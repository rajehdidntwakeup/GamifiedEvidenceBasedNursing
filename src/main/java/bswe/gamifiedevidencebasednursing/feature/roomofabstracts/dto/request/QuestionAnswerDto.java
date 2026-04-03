package bswe.gamifiedevidencebasednursing.feature.roomofabstracts.dto.request;

public class QuestionAnswerDto {
  private long questionId;
  private long answerId;

  public QuestionAnswerDto(long questionId, long answerId) {
    this.questionId = questionId;
    this.answerId = answerId;
  }

  public long getQuestionId() {
    return questionId;
  }

  public long getAnswerId() {
    return answerId;
  }

  public void setQuestionId(long questionId) {
    this.questionId = questionId;
  }

  public void setAnswerId(long answerId) {
    this.answerId = answerId;
  }
}
