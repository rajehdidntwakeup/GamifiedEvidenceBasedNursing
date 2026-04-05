package bswe.gamifiedevidencebasednursing.feature.roomofknowledge.dto.response;



public class GameResponseDto {

  private long gameId;

  public GameResponseDto(long gameId) {
    this.gameId = gameId;
  }

  public long getGameId() {
    return gameId;
  }

  public void setGameId(long gameId) {
    this.gameId = gameId;
  }
}
