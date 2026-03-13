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

}
