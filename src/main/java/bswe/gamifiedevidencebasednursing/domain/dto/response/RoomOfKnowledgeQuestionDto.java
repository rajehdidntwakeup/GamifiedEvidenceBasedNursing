package bswe.gamifiedevidencebasednursing.domain.dto.response;

import java.util.List;

public class RoomOfKnowledgeQuestionDto {

  private final String question;
  private final List<RoomOfKnowledgeAnswerDto> answers;

  public RoomOfKnowledgeQuestionDto(String question, List<RoomOfKnowledgeAnswerDto> answers) {
    this.question = question;
    this.answers = answers;
  }

  public String getQuestion() {
    return question;
  }

  public List<RoomOfKnowledgeAnswerDto> getAnswers() {
    return answers;
  }
}
