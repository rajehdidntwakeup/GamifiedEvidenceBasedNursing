package bswe.gamifiedevidencebasednursing.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Question {

  @Id
  private Long id;
  @NotBlank
  @Lob
  private String title;
  @ManyToMany(mappedBy = "questions")
  private Set<Document> documents = new HashSet<>();
  @ManyToMany(mappedBy = "questions")
  private Set<Room> rooms = new HashSet<>();
  @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
  private Set<Answer> answers = new HashSet<>();
  @ManyToMany(mappedBy = "questions")
  private Set<Mission> missions = new HashSet<>();
  @OneToMany(mappedBy = "question")
  private Set<OpenQuestionAnswer> openQuestionAnswers = new HashSet<>();
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

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Set<Document> getDocuments() {
    return documents;
  }

  public void setDocuments(Set<Document> documents) {
    this.documents = documents;
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
