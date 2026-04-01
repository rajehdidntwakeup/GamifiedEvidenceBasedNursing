package bswe.gamifiedevidencebasednursing.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
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
  private int progress = 0;
  private int extraTime; // in minutes
  @ManyToOne
  @JoinColumn(name = "team_id")
  private Team team;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "room_question",
      joinColumns = @JoinColumn(name = "room_id"),
      inverseJoinColumns = @JoinColumn(name = "question_id"))
  private Set<Question> questions = new HashSet<>();
  @ManyToOne
  @JoinColumn(name = "location_id")
  private Location location;

  public Room() {
  }

  public Room(int extraTime) {
    this.extraTime = extraTime;
  }

  public Room(int extraTime, Team team) {
    this.extraTime = extraTime;
    this.team = team;
  }


  public Long getId() {
    return id;
  }

  public int getExtraTime() {
    return extraTime;
  }

  public void setExtraTime(int timer) {
    this.extraTime = timer;
  }

  public int getProgress() {
    return progress;
  }

  public void setProgress(int progress) {
    this.progress = progress;
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

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

}
