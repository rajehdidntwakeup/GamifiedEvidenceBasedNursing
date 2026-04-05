package bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.dto.response;

public class AnswerDto {
  private long answerId;
  private String answer;

  public AnswerDto() {
  }

  public AnswerDto(long answerId, String answer) {
    this.answerId = answerId;
    this.answer = answer;
  }

  public long getAnswerId() {
    return answerId;
  }

  public void setAnswerId(long answerId) {
    this.answerId = answerId;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }
}
