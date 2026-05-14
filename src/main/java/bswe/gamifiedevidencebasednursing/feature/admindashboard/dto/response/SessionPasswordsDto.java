package bswe.gamifiedevidencebasednursing.feature.admindashboard.dto.response;

import java.util.List;

public class SessionPasswordsDto {
  private long gameId;
  private List<MissionPasswordDto> missionPasswords;


  public SessionPasswordsDto() {
  }

  public SessionPasswordsDto(long gameId, List<MissionPasswordDto> missionPasswords) {
    this.gameId = gameId;
    this.missionPasswords = missionPasswords == null ? null : List.copyOf(missionPasswords);
  }

  public long getGameId() {
    return gameId;
  }

  public void setGameId(long gameId) {
    this.gameId = gameId;
  }

  public List<MissionPasswordDto> getMissionPasswords() {
    return missionPasswords == null ? null : List.copyOf(missionPasswords);
  }

  public void setMissionPasswords(List<MissionPasswordDto> missionPasswords) {
    this.missionPasswords = missionPasswords == null ? null : List.copyOf(missionPasswords);
  }
}
