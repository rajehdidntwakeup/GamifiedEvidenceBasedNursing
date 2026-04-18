package bswe.gamifiedevidencebasednursing.feature.gamecreation.dto.response;


import java.util.List;

public class GameResponseDto {

  private long gameId;
  private List<TeamPassword> teamPasswords;


  public GameResponseDto(long gameId) {
    this.gameId = gameId;
  }

  public GameResponseDto(long gameId, List<TeamPassword> teamPasswords) {
    this.gameId = gameId;
    this.teamPasswords = teamPasswords;
  }

  public long getGameId() {
    return gameId;
  }

  public void setGameId(long gameId) {
    this.gameId = gameId;
  }

  public List<TeamPassword> getTeamPasswords() {
    return teamPasswords;
  }

  public void setTeamPasswords(List<TeamPassword> teamPasswords) {}
}
