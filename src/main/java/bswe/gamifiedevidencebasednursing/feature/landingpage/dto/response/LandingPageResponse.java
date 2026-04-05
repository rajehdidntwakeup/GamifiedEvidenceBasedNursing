package bswe.gamifiedevidencebasednursing.feature.landingpage.dto.response;

import java.util.List;

public class LandingPageResponse {
  private long gameId;
  private List<MissionDto> missions;

  public LandingPageResponse() {
  }

  public LandingPageResponse(long gameId, List<MissionDto> missions) {
    this.gameId = gameId;
    this.missions = missions;
  }

  public long getGameId() {
    return gameId;
  }
  public List<MissionDto> getMissions() {
    return missions;
  }
}
