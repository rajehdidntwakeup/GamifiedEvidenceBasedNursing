package bswe.gamifiedevidencebasednursing.service;

import static bswe.gamifiedevidencebasednursing.domain.enums.Status.READY;

import java.util.HashSet;
import java.util.List;

import bswe.gamifiedevidencebasednursing.domain.Question;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.domain.dto.response.RoomOfKnowledgeQuestionDto;
import bswe.gamifiedevidencebasednursing.domain.enums.Location;
import bswe.gamifiedevidencebasednursing.domain.enums.Mission;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

  private final RoomRepository roomRepository;
  private final QuestionService questionService;

  public RoomService(RoomRepository roomRepository, QuestionService questionService) {
    this.roomRepository = roomRepository;
    this.questionService = questionService;
  }


  public void createRoomOfKnowledge(Team team) {
    Room room = new Room(Location.ROOM_OF_KNOWLEDGE, READY, 10);
    List<Question> questionList = questionService.getRoomOfKnowledgeQuestionList();
    room.setQuestions(new HashSet<>(questionList));
    room.setTeam(team);
    room = roomRepository.save(room);
    if (room.getId() == null) {
      throw new IllegalStateException("Failed to create room");
    }
  }

  public List<RoomOfKnowledgeQuestionDto> getRoomOfKnowledgeQuestionList(long gameId, Mission mission) {
    Room room = roomRepository.findRoomByTeamAndMission(gameId, mission);
    if (room != null) {
      if (room.getQuestions() != null || room.getQuestions().isEmpty()) {
        for (Question question : room.getQuestions()) {

        }
      }
    }

    return null;
  }
}
