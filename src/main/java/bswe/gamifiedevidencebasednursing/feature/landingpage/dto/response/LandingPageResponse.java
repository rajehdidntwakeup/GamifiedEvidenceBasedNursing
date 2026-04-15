package bswe.gamifiedevidencebasednursing.feature.landingpage.dto.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LandingPageResponse {
  private long gameId;
  private List<MissionDto> missions;

  public LandingPageResponse() {
  }

  public LandingPageResponse(long gameId, List<MissionDto> missions) {
    this.gameId = gameId;
    this.missions = missions != null ? new ArrayList<>(missions) : null;
  }

  public long getGameId() {
    return gameId;
  }
  public List<MissionDto> getMissions() {
    return missions != null ? Collections.unmodifiableList(missions) : null;
  }
}
