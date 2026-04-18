package bswe.gamifiedevidencebasednursing.feature.roomtime.dto;

public class RoomTimeResponse {
  private long minutes;
  private long seconds;

  public RoomTimeResponse(long minutes, long seconds) {
    this.minutes = minutes;
    this.seconds = seconds;
  }

  public long getMinutes() {
    return minutes;
  }

  public void setMinutes(long minutes) {
    this.minutes = minutes;
  }

  public long getSeconds() {
    return seconds;
  }

  public void setSeconds(long seconds) {
    this.seconds = seconds;
  }
}
