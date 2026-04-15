package bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.dto.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableQuestionDto {
  private long questionId;
  private String question;
  private List<AnswerDto> answers;

  public TableQuestionDto() {
  }

  public TableQuestionDto(String question, List<AnswerDto> answers) {
    this.question = question;
    this.answers = answers != null ? new ArrayList<>(answers) : null;
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
  public List<AnswerDto> getAnswers() {
    return answers != null ? Collections.unmodifiableList(answers) : null;
  }

  public void setQuestion(String question) {
    this.question = question;
  }
  public void setAnswers(List<AnswerDto> answers) {
    this.answers = answers != null ? new ArrayList<>(answers) : null;
  }
}
