package bswe.gamifiedevidencebasednursing.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Game {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String password;
  private Instant begin;
  private Instant finish;
  @OneToMany(mappedBy = "game",
      orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  private List<Team> teamList = new ArrayList<>();


  public Game() {
  }

  public Game(String password, Instant begin, Instant finish) {
    this.password = password;
    this.begin = begin;
    this.finish = finish;
  }

  public Long getId() {
    return id;
  }

  public String getPassword() {
    return password;
  }

  public Instant getBegin() {
    return begin;
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
