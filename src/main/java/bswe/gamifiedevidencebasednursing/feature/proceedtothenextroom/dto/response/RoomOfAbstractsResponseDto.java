package bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.dto.response;

import java.util.List;

public class RoomOfAbstractsResponseDto {
  private long roomId;
  private long missionId;
  private String mainQuestion;
  private List<String> docs;
  private List<TableQuestionDto> questions;

  public RoomOfAbstractsResponseDto() {
  }

  public RoomOfAbstractsResponseDto(long roomId, long missionId, String mainQuestion, List<String> docs) {
    this.roomId = roomId;
    this.missionId = missionId;
    this.mainQuestion = mainQuestion;

  }

  public long getRoomId() {
    return roomId;
  }

  public long getMissionId() {
    return missionId;
  }

  public String getMainQuestion() {
    return mainQuestion;
  }

  public List<TableQuestionDto> getQuestions() {
    return questions;
  }

  public List<String> getDocs() {
    return docs;
  }

  public void setDocs(List<String> docs) {
    this.docs = docs;
  }

  public void setQuestions(List<TableQuestionDto> questions) {
    this.questions = questions;
  }

  public void setRoomId(long roomId) {
    this.roomId = roomId;
  }

  public void setMissionId(long missionId) {
    this.missionId = missionId;
  }

  public void setMainQuestion(String mainQuestion) {
    this.mainQuestion = mainQuestion;
  }

  public void setQuestions(TableQuestionDto questions) {
    this.questions.add(questions);
  }


}
