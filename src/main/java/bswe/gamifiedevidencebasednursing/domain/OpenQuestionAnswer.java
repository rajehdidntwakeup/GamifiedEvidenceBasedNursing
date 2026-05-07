package bswe.gamifiedevidencebasednursing.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class OpenQuestionAnswer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Lob
  private String answerText;

  private boolean isApproved;

  @ManyToOne
  @JoinColumn(name = "room_id")
  @NotNull
  private Room room;

  @ManyToOne
  @JoinColumn(name = "question_id")
  @NotNull
  private Question question;


  public OpenQuestionAnswer() {
  }

  public OpenQuestionAnswer(long id, String answerText, Room room, Question question) {
    this.id = id;
    this.answerText = answerText;
    this.room = room;
    this.question = question;
  }

  public OpenQuestionAnswer(long id, String answerText, boolean isApproved, Room room, Question question) {
    this.id = id;
    this.answerText = answerText;
    this.isApproved = isApproved;
    this.room = room;
    this.question = question;
  }

  public boolean isApproved() {
    return isApproved;
  }

  public void setApproved(boolean isApproved) {
    this.isApproved = isApproved;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getAnswerText() {
    return answerText;
  }

  public void setAnswerText(String answerText) {
    this.answerText = answerText;
  }

  public Room getRoom() {
    return room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }
}
