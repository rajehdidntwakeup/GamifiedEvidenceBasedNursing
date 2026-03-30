package bswe.gamifiedevidencebasednursing.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Question {

  @Id
  private Long id;
  private String title;
  @ManyToMany(mappedBy = "questions")
  private Set<Image> images = new HashSet<>();
  @ManyToMany(mappedBy = "questions")
  private Set<Room> rooms = new HashSet<>();
  @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
  private Set<Answer> answers = new HashSet<>();
  @ManyToMany(mappedBy = "questions")
  private Set<Mission> missions = new HashSet<>();
  @ManyToOne
  @JoinColumn(name = "location_id")
  private Location location;

  public Question() {
  }

  public Question(String title, Location location) {
    this.title = title;
    this.location = location;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Set<Image> getImages() {
    return images;
  }

  public void setImages(Set<Image> images) {
    this.images = images;
  }

  public Set<Room> getRooms() {
    return rooms;
  }

  public void setRooms(Set<Room> rooms) {
    this.rooms = rooms;
  }

  public Set<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(Set<Answer> answers) {
    this.answers = answers;
  }

  public Set<Mission> getMissions() {
    return missions;
  }

  public void setMissions(Set<Mission> missions) {
    this.missions = missions;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }
}
