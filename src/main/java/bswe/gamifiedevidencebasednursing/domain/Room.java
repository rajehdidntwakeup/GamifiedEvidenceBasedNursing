package bswe.gamifiedevidencebasednursing.domain;

import java.util.HashSet;
import java.util.Set;

import bswe.gamifiedevidencebasednursing.domain.enums.Location;
import bswe.gamifiedevidencebasednursing.domain.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Room {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Enumerated(EnumType.STRING)
  private Location location;
  @Enumerated(EnumType.STRING)
  private Status status;
  private int timer;
  @ManyToOne
  @JoinColumn(name = "team_id")
  private Team team;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "room_question",
      joinColumns = @JoinColumn(name = "room_id"),
      inverseJoinColumns = @JoinColumn(name = "question_id"))
  private Set<Question> questions = new HashSet<>();

  public Room() {
  }

  public Room(Location location, Status status, int timer) {
    this.location = location;
    this.status = status;
    this.timer = timer;
  }

  public Room(Location location, Status status, int timer, Team team) {
    this.location = location;
    this.status = status;
    this.timer = timer;
    this.team = team;
  }

  public Long getId() {
    return id;
  }
  public Location getLocation() {
    return location;
  }
  public Status getStatus() {
    return status;
  }
  public int getTimer() {
    return timer;
  }
  public Team getTeam() {
    return team;
  }
  public void setTeam(Team team) {
    this.team = team;
  }
  public Set<Question> getQuestions() {
    return questions;
  }
  public void setQuestions(Set<Question> questions) {
    this.questions = questions;
  }

}
