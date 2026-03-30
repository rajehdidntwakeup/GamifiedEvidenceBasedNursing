package bswe.gamifiedevidencebasednursing.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank
  @Size(max = 255)
  private String path;
  @ManyToMany
  @JoinTable(name = "image_question",
      joinColumns = @JoinColumn(name = "image_id"),
      inverseJoinColumns = @JoinColumn(name = "question_id"))
  private Set<Question> questions = new HashSet<>();


  public Image() {
  }

  public Image(String path) {
    this.path = path;
  }

  public Long getId() {
    return id;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Set<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(Set<Question> questions) {
    this.questions = questions;
  }
}
