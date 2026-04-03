package bswe.gamifiedevidencebasednursing.feature.roomofabstracts.service;

import bswe.gamifiedevidencebasednursing.domain.Answer;
import bswe.gamifiedevidencebasednursing.domain.Question;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.feature.roomofabstracts.dto.request.QuestionAnswerDto;
import bswe.gamifiedevidencebasednursing.feature.roomofabstracts.dto.request.VerifyRoomOfAbstractsAnswersDto;
import bswe.gamifiedevidencebasednursing.feature.roomofabstracts.dto.response.ResultDto;
import bswe.gamifiedevidencebasednursing.repository.QuestionRepository;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomOfAbstractsService {
  private final QuestionRepository questionRepository;
  private final RoomRepository roomRepository;

  public RoomOfAbstractsService(QuestionRepository questionRepository, RoomRepository roomRepository) {
    this.questionRepository = questionRepository;
    this.roomRepository = roomRepository;
  }

  public ResultDto verifyRoomOfAbstractsAnswers(VerifyRoomOfAbstractsAnswersDto verifyRoomOfAbstractsAnswersDto) {
    Long roomId = verifyRoomOfAbstractsAnswersDto.getRoomId();
    List<QuestionAnswerDto> answers = verifyRoomOfAbstractsAnswersDto.getAnswers();

    // Find the room
    Optional<Room> roomOptional = roomRepository.findById(roomId);
    if (roomOptional.isEmpty()) {
      throw new IllegalArgumentException("Room not found");
    }

    Room room = roomOptional.get();

    // Check if all answers are correct and calculate progress
    int correctCount = 0;
    for (QuestionAnswerDto questionAnswer : answers) {
      Optional<Question> questionOptional = questionRepository.findById(questionAnswer.getQuestionId());
      if (questionOptional.isPresent()) {
        Question question = questionOptional.get();
        for (Answer answer : question.getAnswers()) {
          if (answer.getId() == questionAnswer.getAnswerId() && answer.isCorrect()) {
            correctCount++;
            break;
          }
        }
      }
    }

    // Update progress: each correct answer adds 100/12 = 8.33% (for 12 questions total)
    // Integer math: each correct answer adds 8%, all 12 give 96%
    // Remaining progress (4%) is added at the end to reach exactly 100%
    int progress = room.getProgress();
    progress += correctCount * 100 / 12;
    // If all 12 are correct but we're at 96%, add remaining 4% to reach 100%
    if (correctCount == 12 && progress < 100) {
      progress = 100;
    }
    if (progress > 100) {
      progress = 100;
    }
    room.setProgress(progress);

    // Save the updated room
    roomRepository.save(room);

    // If progress is 100, get the key from location
    if (progress >= 100) {
      String locationKey = room.getLocation().getKey();
      return new ResultDto(progress, locationKey);
    }

    return new ResultDto(progress, null);
  }
}
