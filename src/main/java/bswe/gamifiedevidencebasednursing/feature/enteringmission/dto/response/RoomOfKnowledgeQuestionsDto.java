package bswe.gamifiedevidencebasednursing.feature.enteringmission.dto.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoomOfKnowledgeQuestionsDto {
  private long questionId;
  private String question;
  private List<AnswerDto> answers;

  public RoomOfKnowledgeQuestionsDto() {
  }

  public RoomOfKnowledgeQuestionsDto(long questionId, String question, List<AnswerDto> answers) {
    this.questionId = questionId;
    this.question = question;
    this.answers = answers != null ? new ArrayList<>(answers) : null;
  }

  public long getQuestionId() {
    return questionId;
  }

  public String getQuestion() {
    return question;
  }

  public List<AnswerDto> getAnswers() {
    return answers != null ? Collections.unmodifiableList(answers) : null;
  }
}
