package bswe.gamifiedevidencebasednursing.domain.dto;

import bswe.gamifiedevidencebasednursing.domain.Location;
import bswe.gamifiedevidencebasednursing.domain.enums.Status;

public class RoomDto {

  private String location;
  private Status status;
  private int timer;
  private long teamId;

  public RoomDto(Location location, Status status, int timer, long teamId) {
    this.location = location.getName();
    this.status = status;
    this.timer = timer;
    this.teamId = teamId;
  }
  public String getLocation() {
    return location;
  }
  public Status getStatus() {
    return status;
  }
  public int getTimer() {
    return timer;
  }
  public long getTeamId() {
    return teamId;
  }

}
