package bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.dto.response;

import java.util.List;

public class TableQuestionDto {
  private long questionId;
  private String question;
  private List<String> answers;

  public TableQuestionDto() {
  }

  public TableQuestionDto(String question, List<String> answers) {
    this.question = question;
    this.answers = answers;
  }
  public long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(long questionId) {
    this.questionId = questionId;
  }

  public String getQuestion() {
    return question;
  }
  public List<String> getAnswers() {
    return answers;
  }

  public void setQuestion(String question) {
    this.question = question;
  }
  public void setAnswers(List<String> answers) {
    this.answers = answers;
  }
}
