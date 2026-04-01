package bswe.gamifiedevidencebasednursing.feature.landingpage.dto.response;

import java.util.List;

public class LandingPageResponse {
  private long gameId;
  private List<String> missions;

  public LandingPageResponse() {
  }

  public LandingPageResponse(long gameId, List<String> missions) {
    this.gameId = gameId;
    this.missions = missions;
  }

  public long getGameId() {
    return gameId;
  }
  public List<String> getMissions() {
    return missions;
  }
}
