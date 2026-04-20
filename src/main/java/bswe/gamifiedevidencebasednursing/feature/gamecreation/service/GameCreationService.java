package bswe.gamifiedevidencebasednursing.feature.gamecreation.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import bswe.gamifiedevidencebasednursing.domain.Game;
import bswe.gamifiedevidencebasednursing.domain.Location;
import bswe.gamifiedevidencebasednursing.domain.Question;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.domain.enums.GameStatus;
import bswe.gamifiedevidencebasednursing.domain.enums.Status;
import bswe.gamifiedevidencebasednursing.feature.gamecreation.dto.response.GameResponseDto;
import bswe.gamifiedevidencebasednursing.feature.gamecreation.dto.response.TeamPassword;
import bswe.gamifiedevidencebasednursing.repository.GameRepository;
import bswe.gamifiedevidencebasednursing.repository.LocationRepository;
import bswe.gamifiedevidencebasednursing.repository.MissionRepository;
import bswe.gamifiedevidencebasednursing.repository.QuestionRepository;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import bswe.gamifiedevidencebasednursing.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GameCreationService {

  private final GameRepository gameRepository;
  private final TeamRepository teamRepository;
  private final MissionRepository missionRepository;
  private final LocationRepository locationRepository;
  private final QuestionRepository questionRepository;
  private final RoomRepository roomRepository;

  private static final String ROOM_OF_KNOWLEDGE = "Room of Knowledge";

  public GameCreationService(GameRepository gameRepository, TeamRepository teamRepository,
                             MissionRepository missionRepository, LocationRepository locationRepository,
                             QuestionRepository questionRepository, RoomRepository roomRepository) {
    this.gameRepository = gameRepository;
    this.teamRepository = teamRepository;
    this.missionRepository = missionRepository;
    this.locationRepository = locationRepository;
    this.questionRepository = questionRepository;
    this.roomRepository = roomRepository;
  }

  public GameResponseDto createGame() {
    closeCreatedAndRunningGameSessions();
    Game game = new Game(GameStatus.CREATED);
    game = gameRepository.save(game);
    List<Team> teams = createTeams(game);
    createRoomOfKnowledge(teams);

    List<TeamPassword> teamPasswords = teams.stream()
        .map(t -> new TeamPassword(t.getId(), t.getMission().getName(), t.getPassword()))
        .toList();

    return new GameResponseDto(game.getId(), teamPasswords);
  }

  private void closeCreatedAndRunningGameSessions() {
    gameRepository.findCreatedOrRunningGame()
        .ifPresent(game -> {
          game.setStatus(GameStatus.FINISHED);
          gameRepository.save(game);
        });
  }

  private List<Team> createTeams(Game game) {
    List<Team> teams = missionRepository.findAll().stream()
        .map(mission -> {
          Team team = new Team();
          team.setGame(game);
          team.setStatus(Status.READY);
          team.setPassword(generatePassword());
          team.setWinner(false);
          team.setMission(mission);

          mission.getTeams().add(team);
          game.getTeamList().add(team);
          return team;
        })
        .toList();

    return teamRepository.saveAll(teams);
  }

  private void createRoomOfKnowledge(List<Team> teams) {
    Location location = locationRepository.findByName(ROOM_OF_KNOWLEDGE)
        .orElseThrow(() -> new IllegalStateException("Failed to find room of knowledge location"));

    List<Question> questions = getRoomOfKnowledgeQuestionList();

    List<Room> rooms = teams.stream()
        .map(team -> {
          Room roomOfKnowledge = new Room();
          roomOfKnowledge.setLocation(location);
          roomOfKnowledge.setQuestions(new HashSet<>(questions));
          roomOfKnowledge.setTeam(team);

          location.getRooms().add(roomOfKnowledge);
          return roomOfKnowledge;
        })
        .toList();

    roomRepository.saveAll(rooms);
  }

  private List<Question> getRoomOfKnowledgeQuestionList() {
    List<Question> questionList = questionRepository.findQuestionsForRoomOfKnowledge();
    Collections.shuffle(questionList);
    return questionList.stream()
        .limit(10)
        .toList();
  }

  private String generatePassword() {
    return UUID.randomUUID()
        .toString()
        .replace("-", "")
        .substring(0, 4)
        .toUpperCase();
  }

}
