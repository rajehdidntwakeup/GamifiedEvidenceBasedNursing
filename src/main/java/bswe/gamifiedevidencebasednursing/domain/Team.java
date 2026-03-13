package bswe.gamifiedevidencebasednursing.domain;

import java.util.ArrayList;
import java.util.List;

import bswe.gamifiedevidencebasednursing.domain.enums.Location;
import bswe.gamifiedevidencebasednursing.domain.enums.Mission;
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

@Entity
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Enumerated(EnumType.STRING)
  private Status status;
  @Enumerated(EnumType.STRING)
  private Location location;
  @Enumerated(EnumType.STRING)
  private Mission mission;
  private boolean isWinner;
  @ManyToOne
  @JoinColumn(name = "game_id")
  private Game game;
  @OneToMany(mappedBy = "team",
      orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  private List<Room> roomList = new ArrayList<>();

  public Team() {
  }

  public Team(Status status, Location location, Mission mission, boolean isWinner) {
    this.status = status;
    this.location = location;
    this.mission = mission;
    this.isWinner = isWinner;
  }

  public Long getId() {
    return id;
  }

  public Status getStatus() {
    return status;
  }

  public Location getLocation() {
    return location;
  }

  public Mission getMission() {
    return mission;
  }

  public boolean isWinner() {
    return isWinner;
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
