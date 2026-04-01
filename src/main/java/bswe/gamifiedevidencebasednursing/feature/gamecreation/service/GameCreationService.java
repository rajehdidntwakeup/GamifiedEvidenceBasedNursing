package bswe.gamifiedevidencebasednursing.feature.gamecreation.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import bswe.gamifiedevidencebasednursing.domain.Game;
import bswe.gamifiedevidencebasednursing.domain.Location;
import bswe.gamifiedevidencebasednursing.domain.Mission;
import bswe.gamifiedevidencebasednursing.domain.Question;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.feature.gamecreation.dto.response.GameResponseDto;
import bswe.gamifiedevidencebasednursing.domain.enums.GameStatus;
import bswe.gamifiedevidencebasednursing.domain.enums.Status;
import bswe.gamifiedevidencebasednursing.repository.GameRepository;
import bswe.gamifiedevidencebasednursing.repository.LocationRepository;
import bswe.gamifiedevidencebasednursing.repository.MissionRepository;
import bswe.gamifiedevidencebasednursing.repository.QuestionRepository;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import bswe.gamifiedevidencebasednursing.repository.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class GameCreationService {

  private final GameRepository gameRepository;
  public final TeamRepository teamRepository;
  public final MissionRepository missionRepository;
  public final LocationRepository locationRepository;
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

  public GameResponseDto createGame(String password) {
    Game game = new Game(password, GameStatus.CREATED);
    game = gameRepository.save(game);
    List<Long> teamIds = createTeams(game);
    createRoomOfKnowledge(teamIds);
    return new GameResponseDto(game.getId());
  }

  public List<Long> createTeams(Game game) {
    List<Long> teamIds = new ArrayList<>();
    List<Mission> missions = missionRepository.findAll();
    for (Mission mission : missions) {
      Team team = new Team();
      team.setGame(game);
      team.setStatus(Status.READY);
      team.setWinner(false);
      team.setMission(mission);
      mission.getTeams().add(team);
      game.getTeamList().add(team);
      team = teamRepository.save(team);
      missionRepository.save(mission);
      gameRepository.save(game);
      teamIds.add(team.getId());
    }
    return teamIds;
  }

  // TODO: to be optimized
  public void createRoomOfKnowledge(List<Long> teamIds) {
    Optional<Location> location = locationRepository.findByName(ROOM_OF_KNOWLEDGE);
    if (location.isEmpty()) {
      throw new IllegalStateException("Failed to find room of knowledge location");
    }
    for (Long teamId : teamIds) {
      Location locationInstant = location.get();
      Room roomOfKnowledge = new Room();
      roomOfKnowledge.setLocation(locationInstant);
      locationInstant.getRooms().add(roomOfKnowledge);
      Team team = teamRepository.findById(teamId)
          .orElseThrow(() -> new IllegalArgumentException("Team with Id " + teamId + " not found"));
      List<Question> questions = getRoomOfKnowledgeQuestionList();
      roomOfKnowledge.setQuestions(new HashSet<>(questions));
      roomOfKnowledge.setTeam(team);
      roomOfKnowledge  = roomRepository.save(roomOfKnowledge);
      locationRepository.save(locationInstant);
      if (roomOfKnowledge.getId() == null) {
        throw new IllegalStateException("Failed to create room");
      }
    }
  }

  public List<Question> getRoomOfKnowledgeQuestionList() {
    List<Question> questionList = questionRepository.findQuestionsForRoomOfKnowledge();
    Collections.shuffle(questionList);
    return questionList.subList(0, 10);
  }
}
