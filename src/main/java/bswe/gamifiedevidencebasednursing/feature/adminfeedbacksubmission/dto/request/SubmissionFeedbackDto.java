package bswe.gamifiedevidencebasednursing.feature.adminfeedbacksubmission.dto.request;

import java.util.List;

public class SubmissionFeedbackDto {
  private long roomId;
  private List<QuestionFeedbackDto> questions;

 public SubmissionFeedbackDto() {
 }

  public SubmissionFeedbackDto(long roomId, List<QuestionFeedbackDto> questions) {
    this.roomId = roomId;
    this.questions = questions == null ? null : List.copyOf(questions);
  }

 public long getRoomId() {
  return roomId;
 }
 public void setRoomId(long roomId) {
   this.roomId = roomId;
 }
  public List<QuestionFeedbackDto> getQuestions() {
    return questions == null ? null : List.copyOf(questions);
  }

  public void setQuestions(List<QuestionFeedbackDto> questions) {
    this.questions = questions == null ? null : List.copyOf(questions);
  }
}
