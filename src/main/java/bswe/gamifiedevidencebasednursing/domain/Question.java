package bswe.gamifiedevidencebasednursing.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Question {

  @Id
  private Long id;
  private String title;
  private String image;
  @ManyToMany(mappedBy = "questions")
  private Set<Room> rooms = new HashSet<>();
  @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
  private Set<Answer> answers = new HashSet<>();


  public Question() {
  }

  public Question(String title) {
    this.title = title;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }
  public String getImage() {
    return image;
  }
  public Set<Room> getRooms() {
    return rooms;
  }
  public Set<Answer> getAnswers() {
    return answers;
  }
}
