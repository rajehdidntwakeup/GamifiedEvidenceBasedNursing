package bswe.gamifiedevidencebasednursing.feature.admindashboard.service;

import java.time.Instant;
import java.util.List;

import bswe.gamifiedevidencebasednursing.domain.Game;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.domain.enums.GameStatus;
import bswe.gamifiedevidencebasednursing.feature.admindashboard.dto.response.MissionPasswordDto;
import bswe.gamifiedevidencebasednursing.feature.admindashboard.dto.response.SessionPasswordsDto;
import bswe.gamifiedevidencebasednursing.repository.GameRepository;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import bswe.gamifiedevidencebasednursing.repository.TeamRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdminDashboardService {

  private final GameRepository gameRepository;
  private final TeamRepository teamRepository;
  private final RoomRepository roomRepository;

  public AdminDashboardService(GameRepository gameRepository, TeamRepository teamRepository,
                               RoomRepository roomRepository) {
    this.gameRepository = gameRepository;
    this.teamRepository = teamRepository;
    this.roomRepository = roomRepository;
  }

  public ResponseEntity<SessionPasswordsDto> getSessionPasswords() {
    Game game = findCreatedOrRunningGame();

    List<MissionPasswordDto> missionPasswordDtoList = teamRepository.findAllMissionPasswordsByGameId(game.getId());
    if (missionPasswordDtoList.isEmpty()) {
      throw new ResponseStatusException(HttpStatusCode.valueOf(404), "No missions found");
    }

    return new ResponseEntity<>(new SessionPasswordsDto(game.getId(), missionPasswordDtoList), HttpStatusCode.valueOf(200));
  }

  public ResponseEntity<Void> closeGameSession() {
    Instant now = Instant.now();
    Game game = findCreatedOrRunningGame();

    List<Team> teams = teamRepository.findByGameIdWithRooms(game.getId());

    boolean allRoomsUnstarted = true;
    for (Team team : teams) {
      for (Room room : team.getRoomList()) {
        if (room.getStartTime() != null || room.getEndTime() != null) {
          allRoomsUnstarted = false;
          break;
        }
      }
      if (!allRoomsUnstarted) {
        break;
      }
    }

    if (allRoomsUnstarted) {
      gameRepository.delete(game);
      return ResponseEntity.ok().build();
    }

    game.setStatus(GameStatus.FINISHED);
    game.setFinish(now);

    int maxRooms = 0;
    for (Team team : teams) {
      List<Room> rooms = team.getRoomList();
      int roomCount = rooms.size();
      if (roomCount > maxRooms) {
        maxRooms = roomCount;
      }
      for (Room room : rooms) {
        if (room.getStartTime() != null && room.getEndTime() == null) {
          room.setEndTime(now);
        } else if (room.getStartTime() == null && room.getEndTime() == null) {
          room.setStartTime(now);
          room.setEndTime(now);
        }
      }
      roomRepository.saveAll(rooms);
    }

    if (maxRooms > 0) {
      for (Team team : teams) {
        team.setWinner(team.getRoomList().size() == maxRooms);
      }
      teamRepository.saveAll(teams);
    }
    gameRepository.save(game);

    return ResponseEntity.ok().build();
  }

  private Game findCreatedOrRunningGame() {
    Game game = gameRepository.findCreatedOrRunningGame().orElse(null);
    if (game == null) {
      throw new ResponseStatusException(HttpStatusCode.valueOf(404), "No running game found");
    }
    return game;
  }
}
