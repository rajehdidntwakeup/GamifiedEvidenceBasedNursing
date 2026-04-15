package bswe.gamifiedevidencebasednursing.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import bswe.gamifiedevidencebasednursing.domain.enums.GameStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Game {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank
  @Size(min = 4, max = 50)
  private String password;
  @NotNull
  @Enumerated(EnumType.STRING)
  private GameStatus status;
  private Instant begin;
  private Instant finish;
  @OneToMany(mappedBy = "game",
      orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  private List<Team> teamList = new ArrayList<>();


  public Game() {
  }

  public Game(String password, GameStatus status) {
    this.password = password;
    this.status = status;
  }

  public Game(String password, GameStatus status, Instant begin, Instant finish) {
    this.password = password;
    this.status = status;
    this.begin = begin;
    this.finish = finish;
  }

  public Long getId() {
    return id;
  }

  public String getPassword() {
    return password;
  }

  public GameStatus getStatus() {
    return status;
  }

  public void setStatus(GameStatus status) {
    this.status = status;
  }

  public Instant getBegin() {
    return begin;
  }

  public void setBegin(Instant begin) {
    this.begin = begin;
  }

  public Instant getFinish() {
    return finish;
  }

  public void setFinish(Instant end) {
    this.finish = end;
  }

  public List<Team> getTeamList() {
    return teamList;
  }

  public void setTeamList(List<Team> teamList) {
    this.teamList = teamList;
  }

}
