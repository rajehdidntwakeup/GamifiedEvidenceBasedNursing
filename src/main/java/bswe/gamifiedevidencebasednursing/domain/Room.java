package bswe.gamifiedevidencebasednursing.domain;

import java.time.Instant;
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
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class Room {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Min(0)
  @Max(100)
  private int progress = 0;
  @Min(0)
  private int extraTime; // in minutes
  private Instant startTime;
  private Instant endTime;
  @ManyToOne
  @JoinColumn(name = "team_id")
  private Team team;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "room_question",
      joinColumns = @JoinColumn(name = "room_id"),
      inverseJoinColumns = @JoinColumn(name = "question_id"))
  private Set<Question> questions = new HashSet<>();
  @OneToMany(mappedBy = "room")
  private Set<OpenQuestionAnswer> openQuestionAnswers = new HashSet<>();
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


  public void setId(Long id) {
    this.id = id;
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

  public void addProgress(int amount) {
    this.progress = Math.min(100, this.progress + amount);
  }

  public Instant getStartTime() {
    return startTime;
  }

  public void setStartTime(Instant startTime) {
    this.startTime = startTime;
  }

  public Instant getEndTime() {
    return endTime;
  }

  public void setEndTime(Instant endTime) {
    this.endTime = endTime;
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

  public Set<OpenQuestionAnswer> getOpenQuestionAnswers() {
    return openQuestionAnswers;
  }

  public void setOpenQuestionAnswers(
      Set<OpenQuestionAnswer> openQuestionAnswers) {
    this.openQuestionAnswers = openQuestionAnswers;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

}
