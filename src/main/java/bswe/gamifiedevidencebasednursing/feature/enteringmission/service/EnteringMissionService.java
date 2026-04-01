package bswe.gamifiedevidencebasednursing.feature.enteringmission.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bswe.gamifiedevidencebasednursing.domain.Answer;
import bswe.gamifiedevidencebasednursing.domain.Game;
import bswe.gamifiedevidencebasednursing.domain.Question;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.domain.enums.GameStatus;
import bswe.gamifiedevidencebasednursing.domain.enums.Status;
import bswe.gamifiedevidencebasednursing.feature.enteringmission.dto.request.EnteringGameRequest;
import bswe.gamifiedevidencebasednursing.feature.enteringmission.dto.response.AnswerDto;
import bswe.gamifiedevidencebasednursing.feature.enteringmission.dto.response.EnteringGameResponse;
import bswe.gamifiedevidencebasednursing.feature.enteringmission.dto.response.RoomOfKnowledgeQuestionsDto;
import bswe.gamifiedevidencebasednursing.repository.GameRepository;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import bswe.gamifiedevidencebasednursing.repository.TeamRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EnteringMissionService {

  private final GameRepository gameRepository;
  private final RoomRepository roomRepository;
  private final TeamRepository teamRepository;
  private static final String ROOM_OF_KNOWLEDGE = "Room of Knowledge";

  public EnteringMissionService(GameRepository gameRepository, RoomRepository roomRepository,
                                TeamRepository teamRepository) {
    this.gameRepository = gameRepository;
    this.roomRepository = roomRepository;
    this.teamRepository = teamRepository;
  }


  public ResponseEntity<EnteringGameResponse> enterMission(EnteringGameRequest request) {
    Optional<Game> game = gameRepository.findById(request.getGameId());
    if (game.isEmpty()) {
      throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Game not found");
    }
    Game gameInstance = game.get();
    if (gameInstance.getPassword().equals(request.getPassword())) {
      startGame(gameInstance);
      return ResponseEntity
          .ok(enterRoomOfKnowledge(request.getGameId(), request.getMissionId()));
    } else {
      throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Invalid game password");
    }
  }

  private EnteringGameResponse enterRoomOfKnowledge(Long gameId, Long missionId) {
    Room room = roomRepository.findRoomByGameIdAndMissionIdAndLocationName(gameId, missionId, ROOM_OF_KNOWLEDGE);
    if (room == null) {
      throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Room not found");
    }
    updateTeamStatus(room.getTeam().getId());
    return new EnteringGameResponse(missionId, room.getId(), getRoomOfKnowledgeQuestions(room));
  }

  private List<RoomOfKnowledgeQuestionsDto> getRoomOfKnowledgeQuestions(Room room) {
    List<RoomOfKnowledgeQuestionsDto> questionsDtoList = new ArrayList<>();
    for (Question question : room.getQuestions()) {
      List<AnswerDto> answersDtoList = new ArrayList<>();
      for (Answer answer : question.getAnswers()) {
        answersDtoList.add(new AnswerDto(answer.getId(), answer.getText()));
      }
      RoomOfKnowledgeQuestionsDto questionDto = new RoomOfKnowledgeQuestionsDto(question.getId(), question.getTitle(), answersDtoList);
      questionsDtoList.add(questionDto);
    }
    return questionsDtoList;
  }

  private void startGame(Game game) {
    if (game.getStatus() == GameStatus.CREATED) {
      Instant start = Instant.now();
      game.setBegin(start);
      game.setStatus(GameStatus.RUNNING);
      gameRepository.save(game);
    }
  }

  private void updateTeamStatus(long teamId) {
    Optional<Team> team = teamRepository.findById(teamId);
    if (team.isPresent()) {
      team.get().setStatus(Status.STARTED);
      teamRepository.save(team.get());
    }
  }
}
