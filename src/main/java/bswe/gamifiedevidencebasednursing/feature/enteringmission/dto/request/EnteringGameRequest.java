package bswe.gamifiedevidencebasednursing.feature.enteringmission.dto.request;

public class EnteringGameRequest {
  private String password;
  private long missionId;

  public EnteringGameRequest() {
  }

  public EnteringGameRequest(String password, long missionId) {
    this.password = password;
    this.missionId = missionId;
  }



  public String getPassword() {
    return password;
  }

  public long getMissionId() {
    return missionId;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setMissionId(long missionId) {
    this.missionId = missionId;
  }
}
