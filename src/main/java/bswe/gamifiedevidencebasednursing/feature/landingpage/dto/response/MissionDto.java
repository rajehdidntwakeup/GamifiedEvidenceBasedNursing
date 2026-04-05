package bswe.gamifiedevidencebasednursing.feature.landingpage.dto.response;

public class MissionDto {
  private long missionId;
  private String missionName;

  public MissionDto() {
  }

  public MissionDto(long missionId, String missionName) {
    this.missionId = missionId;
    this.missionName = missionName;
  }

  public long getMissionId() {
    return missionId;
  }

  public void setMissionId(long missionId) {
    this.missionId = missionId;
  }

  public String getMissionName() {
    return missionName;
  }

  public void setMissionName(String missionName) {
    this.missionName = missionName;
  }
}
