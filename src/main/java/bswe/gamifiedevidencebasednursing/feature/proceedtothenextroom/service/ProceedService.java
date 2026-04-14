package bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import bswe.gamifiedevidencebasednursing.domain.Document;
import bswe.gamifiedevidencebasednursing.domain.Location;
import bswe.gamifiedevidencebasednursing.domain.Question;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.dto.request.ProceedDto;
import bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.dto.response.AnswerDto;
import bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.dto.response.RoomResponseDto;
import bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.dto.response.TableQuestionDto;
import bswe.gamifiedevidencebasednursing.repository.LocationRepository;
import bswe.gamifiedevidencebasednursing.repository.QuestionRepository;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import bswe.gamifiedevidencebasednursing.repository.TeamRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProceedService {

  private final RoomRepository roomRepository;
  private final TeamRepository teamRepository;
  private final LocationRepository locationRepository;
  private final QuestionRepository questionRepository;
  private static final String ROOM_OF_KNOWLEDGE = "Room of Knowledge";
  private static final String ROOM_OF_ABSTRACTS = "Room of Abstracts";
  private static final String ROOM_OF_ANALYTICS = "Room of Analytics";


  public ProceedService(RoomRepository roomRepository, TeamRepository teamRepository,
                        LocationRepository locationRepository, QuestionRepository questionRepository) {
    this.roomRepository = roomRepository;
    this.teamRepository = teamRepository;
    this.locationRepository = locationRepository;
    this.questionRepository = questionRepository;
  }

  public ResponseEntity<RoomResponseDto> proceedToTheRoomOfAbstracts(ProceedDto proceedDto) {
    return proceedToNextRoom(proceedDto, ROOM_OF_KNOWLEDGE, ROOM_OF_ABSTRACTS);
  }

  public ResponseEntity<RoomResponseDto> proceedToTheRoomOfAnalytics(ProceedDto proceedDto) {
    return proceedToNextRoom(proceedDto, ROOM_OF_ABSTRACTS, ROOM_OF_ANALYTICS);
  }

  private ResponseEntity<RoomResponseDto> proceedToNextRoom(ProceedDto proceedDto, String currentRoomName,
                                                            String nextRoomName) {
    return roomRepository.findById(proceedDto.roomId())
        .filter(room -> room.getProgress() == 100)
        .filter(room -> currentRoomName.equals(room.getLocation().getName()))
        .map(room -> ResponseEntity.ok(createRoom(room.getTeam().getId(), nextRoomName)))
        .orElse(ResponseEntity.notFound().build());
  }

  private RoomResponseDto createRoom(long teamId, String nextRoomName) {
    Optional<Team> team = teamRepository.findById(teamId);
    Optional<Location> location = locationRepository.findByName(nextRoomName);

    if (team.isPresent() && location.isPresent()) {
      Team currentTeam = team.get();
      Location nextLocation = location.get();
      Room nextRoom = new Room();
      nextRoom.setLocation(nextLocation);
      nextLocation.getRooms().add(nextRoom);

      Set<Question> questions = new HashSet<>(
          questionRepository.findByLocationIdAndMissionId(nextLocation.getId(), currentTeam.getMission().getId()));
      nextRoom.setQuestions(questions);
      nextRoom.setTeam(currentTeam);
      nextRoom = roomRepository.save(nextRoom);
      locationRepository.save(nextLocation);

      if (nextRoom.getId() == null) {
        throw new IllegalStateException("Failed to create " + nextRoomName.toLowerCase());
      }
      return createRoomResponse(currentTeam.getMission().getId(), nextRoom.getId(), questions);
    }
    return null;
  }

  private RoomResponseDto createRoomResponse(long missionId, long roomId, Set<Question> questions) {
    RoomResponseDto roomResponseDto = new RoomResponseDto();
    roomResponseDto.setRoomId(roomId);
    roomResponseDto.setMissionId(missionId);

    List<TableQuestionDto> tableQuestionDtos = new ArrayList<>();
    for (Question question : questions) {
      if (question.getAnswers().isEmpty() && !question.getDocuments().isEmpty()) {
        roomResponseDto.setMainQuestion(question.getTitle());
        roomResponseDto.setDocs(question.getDocuments().stream()
            .map(Document::getPath)
            .toList());
      } else {
        TableQuestionDto tableQuestionDto = new TableQuestionDto();
        tableQuestionDto.setQuestionId(question.getId());
        tableQuestionDto.setQuestion(question.getTitle());
        tableQuestionDto.setAnswers(question.getAnswers().stream()
            .map(answer -> new AnswerDto(answer.getId(), answer.getText()))
            .toList());
        tableQuestionDtos.add(tableQuestionDto);
      }
    }
    roomResponseDto.setQuestions(tableQuestionDtos);
    return roomResponseDto;
  }
}
