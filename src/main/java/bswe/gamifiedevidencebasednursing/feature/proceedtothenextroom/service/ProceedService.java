package bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import bswe.gamifiedevidencebasednursing.domain.Answer;
import bswe.gamifiedevidencebasednursing.domain.Image;
import bswe.gamifiedevidencebasednursing.domain.Location;
import bswe.gamifiedevidencebasednursing.domain.Question;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.dto.request.ProceedDto;
import bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.dto.response.RoomOfAbstractsResponseDto;
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


  public ProceedService(RoomRepository roomRepository, TeamRepository teamRepository,
                        LocationRepository locationRepository, QuestionRepository questionRepository) {
    this.roomRepository = roomRepository;
    this.teamRepository = teamRepository;
    this.locationRepository = locationRepository;
    this.questionRepository = questionRepository;
  }

  public ResponseEntity<RoomOfAbstractsResponseDto> proceedToTheNextRoom(ProceedDto proceedDto) {
    Optional<Room> room = roomRepository.findById(proceedDto.roomId());
    if (room.isPresent()) {
      Room currentRoom = room.get();
      Location location = currentRoom.getLocation();
      if (location.getName().equals(ROOM_OF_KNOWLEDGE)) {
        proceedToRoomOfAbstracts(currentRoom.getTeam().getId());
        return ResponseEntity.ok(proceedToRoomOfAbstracts(currentRoom.getTeam().getId()));
      }
    }
    return ResponseEntity.notFound().build();
  }

  private RoomOfAbstractsResponseDto proceedToRoomOfAbstracts(long teamId) {
    Optional<Team> team = teamRepository.findById(teamId);
    Optional<Location> location = locationRepository.findByName(ROOM_OF_ABSTRACTS);
    if (team.isPresent() && location.isPresent()) {
      Team currentTeam = team.get();
      Location nextLocation = location.get();
      Room roomOfAbstracts = new Room();
      roomOfAbstracts.setLocation(nextLocation);
      nextLocation.getRooms().add(roomOfAbstracts);
      Set<Question> questions = getQuestionsForRoomOfAbstracts(currentTeam.getMission().getId(), nextLocation.getId());
      roomOfAbstracts.setQuestions(questions);
      roomOfAbstracts.setTeam(currentTeam);
      roomOfAbstracts = roomRepository.save(roomOfAbstracts);
      locationRepository.save(nextLocation);
      if (roomOfAbstracts.getId() == null) {
        throw new IllegalStateException("Failed to create room");
      }
      return createRoomOfAbstractsResponse(currentTeam.getMission().getId(), roomOfAbstracts.getId(), questions);
    }
    return null;
  }

  private Set<Question> getQuestionsForRoomOfAbstracts(long missionId, long locationId) {
    List<Question> questions = questionRepository.findByLocationIdAndMissionId(locationId, missionId);
    return new HashSet<>(questions);
  }

  private RoomOfAbstractsResponseDto createRoomOfAbstractsResponse(long missionId, long roomId, Set<Question> questions) {
    RoomOfAbstractsResponseDto roomOfAbstractsResponseDto = new RoomOfAbstractsResponseDto();
    roomOfAbstractsResponseDto.setRoomId(roomId);
    roomOfAbstractsResponseDto.setMissionId(missionId);
    List<TableQuestionDto> tableQuestionDtos = new ArrayList<>();
    for (Question question : questions) {
      if (question.getAnswers().isEmpty()) {
        roomOfAbstractsResponseDto.setMainQuestion(question.getTitle());
        List<String> images = new ArrayList<>();
        for (Image image : question.getImages()) {
          images.add(image.getPath());
        }
        roomOfAbstractsResponseDto.setImages(images);
      } else {
        TableQuestionDto tableQuestionDto = new TableQuestionDto();
        tableQuestionDto.setQuestionId(question.getId());
        tableQuestionDto.setQuestion(question.getTitle());
        List<String> answers = new ArrayList<>();
        for (Answer answer : question.getAnswers()) {
          answers.add(answer.getText());
        }
        tableQuestionDto.setAnswers(answers);
        tableQuestionDtos.add(tableQuestionDto);
      }
    }
    roomOfAbstractsResponseDto.setQuestions(tableQuestionDtos);
    return roomOfAbstractsResponseDto;
  }
}
