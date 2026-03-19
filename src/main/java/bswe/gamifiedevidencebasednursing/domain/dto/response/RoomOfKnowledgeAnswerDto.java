package bswe.gamifiedevidencebasednursing.domain.dto.response;

public class RoomOfKnowledgeAnswerDto {

  private final String answer;
  private final boolean isCorrect;

  public RoomOfKnowledgeAnswerDto(String answer, boolean isCorrect) {
    this.answer = answer;
    this.isCorrect = isCorrect;
  }

  public String getAnswer() {
    return answer;
  }

  public boolean isCorrect() {
    return isCorrect;
  }
}
