package bswe.gamifiedevidencebasednursing.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Answer {

  @Id
  private Long id;
  private String text;
  private boolean isCorrect;
  @ManyToOne
  @JoinColumn(name = "question_id")
  private Question question;

  public Answer() {
  }

  public Long getId() {
    return id;
  }

  public String getText() {
    return text;
  }

  public boolean isCorrect() {
    return isCorrect;
  }

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }
}
