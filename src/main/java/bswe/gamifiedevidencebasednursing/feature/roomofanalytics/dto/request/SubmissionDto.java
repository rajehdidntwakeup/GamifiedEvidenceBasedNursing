package bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.request;

import java.util.List;

public class SubmissionDto {

  private long roomId;
  private long levelofEvidenceQuestionId;
  private String levelofEvidenceAnswer;
  private List<OpenQuestionSubmissionDto> openQuestions;

  public SubmissionDto() {
  }

  public SubmissionDto(long roomId, List<OpenQuestionSubmissionDto> openQuestions) {
    this.roomId = roomId;
    this.openQuestions = openQuestions == null ? null : List.copyOf(openQuestions);
  }

  public long getRoomId() {
    return roomId;
  }

  public void setRoomId(long roomId) {
    this.roomId = roomId;
  }

  public List<OpenQuestionSubmissionDto> getOpenQuestions() {
    return openQuestions == null ? null : List.copyOf(openQuestions);
  }

  public void setOpenQuestions(
      List<OpenQuestionSubmissionDto> openQuestions) {
    this.openQuestions = openQuestions == null ? null : List.copyOf(openQuestions);
  }

  public long getLevelofEvidenceQuestionId() {
    return levelofEvidenceQuestionId;
  }
  public void setLevelofEvidenceQuestionId(long levelofEvidenceQuestionId) {
    this.levelofEvidenceQuestionId = levelofEvidenceQuestionId;
  }
  public String getLevelofEvidenceAnswer() {
    return levelofEvidenceAnswer;
  }
  public void setLevelofEvidenceAnswer(String levelofEvidenceAnswer) {
    this.levelofEvidenceAnswer = levelofEvidenceAnswer;
  }
}
