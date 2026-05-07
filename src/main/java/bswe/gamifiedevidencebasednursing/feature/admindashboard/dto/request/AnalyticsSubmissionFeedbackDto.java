package bswe.gamifiedevidencebasednursing.feature.admindashboard.dto.request;

import java.util.List;

public class AnalyticsSubmissionFeedbackDto {
  private long roomId;
 List<QuestionFeedbackDto> questions;

 public AnalyticsSubmissionFeedbackDto() {
 }

  public AnalyticsSubmissionFeedbackDto(long roomId, List<QuestionFeedbackDto> questions) {
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
