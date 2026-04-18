package bswe.gamifiedevidencebasednursing.feature.enteringmission.dto.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnteringGameResponse {
  private long missionId;
  private long roomId;
  private int timer;

  private List<RoomOfKnowledgeQuestionsDto> questions = new ArrayList<>();

  public EnteringGameResponse() {
  }

  public EnteringGameResponse(long missionId, long roomId, int timer, List<RoomOfKnowledgeQuestionsDto> questions) {
    this.missionId = missionId;
    this.roomId = roomId;
    this.timer = timer;
    this.questions = questions != null ? new ArrayList<>(questions) : new ArrayList<>();
  }

  public long getMissionId() {
    return missionId;
  }

  public long getRoomId() {
    return roomId;
  }

  public int getTimer() {
    return timer;
  }

  public List<RoomOfKnowledgeQuestionsDto> getQuestions() {
    return questions != null ? Collections.unmodifiableList(questions) : null;
  }
}
