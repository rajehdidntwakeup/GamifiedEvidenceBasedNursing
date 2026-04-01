package bswe.gamifiedevidencebasednursing.feature.enteringmission.dto.response;

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

  public String getAnswer() {
    return answer;
  }
}
