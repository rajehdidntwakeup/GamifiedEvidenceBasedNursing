package bswe.gamifiedevidencebasednursing.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Location {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank
  @Size(max = 255)
  private String name;

  @Column(name = "location_key")
  private String key;

  private int timer; // in minutes

  @OneToMany(mappedBy = "location")
  private List<Question> questions = new ArrayList<>();
  @OneToMany(mappedBy = "location")
  private List<Room> rooms = new ArrayList<>();

  public Location() {
  }

  public Location(String name, String key, int timer) {
    this.name = name;
    this.key = key;
    this.timer = timer;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public int getTimer() {
    return timer;
  }

  public void setTimer(int timer) {
    this.timer = timer;
  }

  public List<Question> getQuestions() {
    return questions;
  }
  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }

  public List<Room> getRooms() {
    return rooms;
  }
  public void setRooms(List<Room> rooms) {
    this.rooms = rooms;
  }

}
