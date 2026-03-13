package bswe.gamifiedevidencebasednursing.domain.dto.response;

import java.util.HashMap;
import java.util.Map;

import bswe.gamifiedevidencebasednursing.domain.enums.Mission;

public class GameResponseDto {

  private long gameId;
  private Map<Mission, Long> teamMissions = new HashMap<>();

  public GameResponseDto(long gameId) {
    this.gameId = gameId;
  }

  public long getGameId() {
    return gameId;
  }

  public void setGameId(long gameId) {
    this.gameId = gameId;
  }

  public Map<Mission, Long> getTeamMissions() {
    return teamMissions;
  }

  public void setTeamMissions(
      Map<Mission, Long> teamMissions) {
    this.teamMissions = teamMissions;
  }
}
