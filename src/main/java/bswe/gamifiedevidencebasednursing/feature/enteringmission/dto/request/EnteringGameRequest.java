package bswe.gamifiedevidencebasednursing.feature.enteringmission.dto.request;

public class EnteringGameRequest {
  private long gameId;
  private String password;
  private long missionId;

  public EnteringGameRequest() {
  }

  public EnteringGameRequest(long gameId, String password, long missionId) {
    this.gameId = gameId;
    this.password = password;
    this.missionId = missionId;
  }

  public long getGameId() {
    return gameId;
  }

  public void setGameId(long gameId) {
    this.gameId = gameId;
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
