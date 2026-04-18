package bswe.gamifiedevidencebasednursing.domain;

import java.util.ArrayList;
import java.util.List;

import bswe.gamifiedevidencebasednursing.domain.enums.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Enumerated(EnumType.STRING)
  @NotNull
  private Status status;
  @NotNull
  private String password;
  private boolean isWinner;
  @ManyToOne
  @JoinColumn(name = "mission_id")
  private Mission mission;
  @ManyToOne
  @JoinColumn(name = "game_id")
  @NotNull
  private Game game;
  @OneToMany(mappedBy = "team",
      orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  private List<Room> roomList = new ArrayList<>();

  public Team() {
  }

  public Team(Status status, Mission mission, boolean isWinner) {
    this.status = status;
    this.mission = mission;
    this.isWinner = isWinner;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Status getStatus() {
    return status;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Mission getMission() {
    return mission;
  }

  public boolean isWinner() {
    return isWinner;
  }

  public void setWinner(boolean winner) {
    this.isWinner = winner;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public void setMission(Mission mission) {
    this.mission = mission;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public Game getGame() {
    return game;
  }

  public List<Room> getRoomList() {
    return roomList;
  }

  public void setRoomList(List<Room> roomList) {
    this.roomList = roomList;
  }
}
