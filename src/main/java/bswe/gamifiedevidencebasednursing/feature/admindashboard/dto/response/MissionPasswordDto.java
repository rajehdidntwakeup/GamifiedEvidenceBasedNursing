package bswe.gamifiedevidencebasednursing.feature.admindashboard.dto.response;

public class MissionPasswordDto {
  private String missionName;
  private String password;

  public MissionPasswordDto(String missionName, String password) {
    this.missionName = missionName;
    this.password = password;
  }

  public String getMissionName() {
    return missionName;
  }

  public String getPassword() {
    return password;
  }
}
