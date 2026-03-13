package bswe.gamifiedevidencebasednursing.domain.dto;

import bswe.gamifiedevidencebasednursing.domain.enums.Location;
import bswe.gamifiedevidencebasednursing.domain.enums.Mission;
import bswe.gamifiedevidencebasednursing.domain.enums.Status;

public class TeamDto {

  private Status status;
  private Location location;
  private Mission mission;
  private boolean isWinner;
  private long sessionId;

  public TeamDto(Status status, Location location, Mission mission, boolean isWinner, long sessionId) {
    this.status = status;
    this.location = location;
    this.mission = mission;
    this.isWinner = isWinner;
    this.sessionId = sessionId;
  }

  public Status getStatus() {
    return status;
  }
  public Location getLocation() {
    return location;
  }
  public Mission getMission() {
    return mission;
  }
  public boolean isWinner() {
    return isWinner;
  }
  public long getSessionId() {
    return sessionId;
  }
}
