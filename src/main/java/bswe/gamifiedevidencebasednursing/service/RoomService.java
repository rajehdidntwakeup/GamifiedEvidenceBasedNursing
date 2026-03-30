package bswe.gamifiedevidencebasednursing.service;

import static bswe.gamifiedevidencebasednursing.domain.enums.Status.READY;

import java.util.HashSet;
import java.util.List;

import bswe.gamifiedevidencebasednursing.domain.Game;
import bswe.gamifiedevidencebasednursing.domain.Mission;
import bswe.gamifiedevidencebasednursing.domain.Question;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.domain.dto.response.RoomOfKnowledgeQuestionDto;
import bswe.gamifiedevidencebasednursing.repository.GameRepository;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import bswe.gamifiedevidencebasednursing.repository.TeamRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RoomService {

  private final RoomRepository roomRepository;
  private final QuestionService questionService;
  private final GameRepository gameRepository;
  private final TeamRepository teamRepository;

  public RoomService(RoomRepository roomRepository, QuestionService questionService, GameRepository gameRepository, TeamRepository teamRepository) {
    this.roomRepository = roomRepository;
    this.questionService = questionService;
    this.gameRepository = gameRepository;
    this.teamRepository = teamRepository;
  }

  public void createRoomOfKnowledge(Team team) {
    Room room = new Room(600); // 10 minutes = 600 seconds
    List<Question> questionList = questionService.getRoomOfKnowledgeQuestionList();
    room.setQuestions(new HashSet<>(questionList));
    room.setTeam(team);
    room = roomRepository.save(room);
    if (room.getId() == null) {
      throw new IllegalStateException("Failed to create room");
    }
  }

  public List<RoomOfKnowledgeQuestionDto> getRoomOfKnowledgeQuestionList(long gameId, Mission mission, String password) {
    Game game = gameRepository.findById(gameId).orElseThrow(() -> new IllegalArgumentException("Game not found"));
    if (game.getPassword() != null && !game.getPassword().equals(password)) {
      throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Invalid password");
    }

    //TODO
    /*
    Room room = roomRepository.findRoomByGameIdAndMission(gameId, mission);
    if (room != null) {
      if (room.getQuestions() != null || room.getQuestions().isEmpty()) {
        return getRoomOfKnowledgeQuestionDtos(room);
      }
    }
     */
    return null;
  }


}
