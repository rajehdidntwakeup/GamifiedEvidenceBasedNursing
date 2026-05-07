package bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.request;

public class OpenQuestionSubmissionDto {
  private long questionId;
  private String answer;

  public OpenQuestionSubmissionDto() {
  }

  public OpenQuestionSubmissionDto(long questionId, String answer) {
    this.questionId = questionId;
    this.answer = answer;
  }

  public long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(long questionId) {
    this.questionId = questionId;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }
}
