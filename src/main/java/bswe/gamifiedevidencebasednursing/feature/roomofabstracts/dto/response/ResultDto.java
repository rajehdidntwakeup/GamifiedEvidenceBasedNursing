package bswe.gamifiedevidencebasednursing.feature.roomofabstracts.dto.response;

public class ResultDto {
  private int progress;
  private String key;

  public ResultDto() {
  }

  public ResultDto(int progress, String key) {
    this.progress = progress;
    this.key = key;
  }

  public int getProgress() {
    return progress;
  }

  public String getKey() {
    return key;
  }

  public void setProgress(int progress) {
    this.progress = progress;
  }

  public void setKey(String key) {
    this.key = key;
  }
}
