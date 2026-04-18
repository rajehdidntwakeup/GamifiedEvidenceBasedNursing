package bswe.gamifiedevidencebasednursing.feature.gamecreation.dto.response;

public class TeamPassword {

  private long teamId;
  private String mission;
  private String password;

  public TeamPassword() {
  }

  public TeamPassword(long teamId, String mission, String password) {
    this.teamId = teamId;
    this.mission = mission;
    this.password = password;
  }

  public long getTeamId() {
    return teamId;
  }

  public void setTeamId(long teamId) {
    this.teamId = teamId;
  }

  public String getMission() {
    return mission;
  }

  public void setMission(String mission) {
    this.mission = mission;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
