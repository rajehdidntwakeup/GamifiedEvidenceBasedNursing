package bswe.gamifiedevidencebasednursing.feature.gamecreation.dto.response;


import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class GameResponseDto {

  private long gameId;
  private List<TeamPassword> teamPasswords = new ArrayList<>();


  public GameResponseDto(long gameId) {
    this.gameId = gameId;
  }

  public GameResponseDto(long gameId, List<TeamPassword> teamPasswords) {
    this.gameId = gameId;
    this.teamPasswords = teamPasswords != null ? new ArrayList<>(teamPasswords) : new ArrayList<>();
  }

  public long getGameId() {
    return gameId;
  }

  public void setGameId(long gameId) {
    this.gameId = gameId;
  }

  public List<TeamPassword> getTeamPasswords() {
    return teamPasswords != null ? Collections.unmodifiableList(teamPasswords) : null;
  }

  public void setTeamPasswords(List<TeamPassword> teamPasswords) {
    this.teamPasswords = teamPasswords != null ? new ArrayList<>(teamPasswords) : new ArrayList<>();
  }
}
