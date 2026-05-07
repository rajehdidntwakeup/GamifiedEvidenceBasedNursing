package bswe.gamifiedevidencebasednursing.feature.admindashboard.dto.request;

public class QuestionFeedbackDto {
  private long questionId;
  private String answer;
  private boolean approved;

  public QuestionFeedbackDto() {

  }

  public QuestionFeedbackDto(long questionId, String answer, boolean approved) {
    this.questionId = questionId;
    this.answer = answer;
    this.approved = approved;
  }

  public long getQuestionId() {
    return questionId;
  }

  public String getAnswer() {
    return answer;
  }

  public boolean isApproved() {
    return approved;
  }

  public void setApproved(boolean approved) {
    this.approved = approved;
  }

  public void setQuestionId(long questionId) {
    this.questionId = questionId;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }
}
