package bswe.gamifiedevidencebasednursing.feature.enteringmission.dto.request;

public class EnteringGameRequest {
  private String password;
  private long gameId;
  private long missionId;

  public EnteringGameRequest() {
  }

  public EnteringGameRequest(String password, long gameId, long missionId) {
    this.password = password;
    this.gameId = gameId;
    this.missionId = missionId;
  }



  public String getPassword() {
    return password;
  }

  public long getMissionId() {
    return missionId;
  }

  public long getGameId() {
    return gameId;
  }

  public void setGameId(long gameId) {
    this.gameId = gameId;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setMissionId(long missionId) {
    this.missionId = missionId;
  }
}
