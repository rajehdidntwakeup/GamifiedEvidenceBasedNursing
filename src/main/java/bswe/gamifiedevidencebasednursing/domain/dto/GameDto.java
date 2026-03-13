package bswe.gamifiedevidencebasednursing.domain.dto;

import java.time.Instant;

public class GameDto {

  private String password;
  private Instant start;
  private Instant end;

  public GameDto() {
  }

  public GameDto(String password, Instant start) {
    this.password = password;
    this.start = start;
  }

  public void setEnd(Instant end) {
    this.end = end;
  }

  public String getPassword() {
    return password;
  }

  public Instant getStart() {
    return start;
  }
  public Instant getEnd() {
    return end;
  }
}
