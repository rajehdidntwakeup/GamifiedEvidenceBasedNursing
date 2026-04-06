package bswe.gamifiedevidencebasednursing.feature.roomofknowledge.service;

import java.util.Optional;

import bswe.gamifiedevidencebasednursing.domain.Answer;
import bswe.gamifiedevidencebasednursing.domain.Question;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.feature.roomofknowledge.dto.request.VerifyAnswerDto;
import bswe.gamifiedevidencebasednursing.feature.roomofknowledge.dto.response.ResultDto;
import bswe.gamifiedevidencebasednursing.repository.AnswerRepository;
import bswe.gamifiedevidencebasednursing.repository.QuestionRepository;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RoomOfKnowledgeService {

  private final RoomRepository roomRepository;
  private final QuestionRepository questionRepository;
  private final AnswerRepository answerRepository;

  public RoomOfKnowledgeService(RoomRepository roomRepository, QuestionRepository questionRepository,
                                AnswerRepository answerRepository) {
    this.roomRepository = roomRepository;
    this.questionRepository = questionRepository;
    this.answerRepository = answerRepository;
  }

  public ResponseEntity<Boolean> isTheAnswerCorrect(VerifyAnswerDto verifyAnswerDto) {
    Optional<Question> question = questionRepository.findById(verifyAnswerDto.getQuestionId());
    Optional<Room> room = roomRepository.findById(verifyAnswerDto.getRoomId());
    if (question.isPresent() && room.isPresent()) {
      for (Answer answer : question.get().getAnswers()) {
        if (answer.getId() == verifyAnswerDto.getAnswerId() && answer.isCorrect()) {
          if (room.get().getProgress() < 100) {
            room.get().setProgress(room.get().getProgress() + 10);
            roomRepository.save(room.get());
            return new ResponseEntity<>(true, HttpStatus.OK);
          } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Room is already completed");
          }
        }
      }
    } else {
     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid question or room ID");
    }
    return new ResponseEntity<>(false, HttpStatus.OK);
  }

  public ResponseEntity<Boolean> verifyAnswer(long roomId, long questionId, long answerId) {
    Optional<Answer> answer = answerRepository.findById(answerId);
    if (answer.isPresent()
        && answer.get().getQuestion().getId() == questionId
        && answer.get().isCorrect()) {
      Optional<Room> room = roomRepository.findById(roomId);
      if (room.isPresent()) {
        if (room.get().getProgress() < 100) {
          room.get().setProgress(room.get().getProgress() + 10);
          roomRepository.save(room.get());
        }
      } else {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid question or room ID");
      }
      return new ResponseEntity<>(true, HttpStatus.OK);
    }
    return new ResponseEntity<>(false, HttpStatus.OK);
  }

  public ResponseEntity<ResultDto> getResult(long roomId) {
    Optional<Room> room = roomRepository.findById(roomId);
    if (room.isPresent()) {
      int progress = room.get().getProgress();
      if (progress >= 100) {
        String key = room.get().getLocation().getKey();
        return new ResponseEntity<>(new ResultDto(progress, key), HttpStatus.OK);
      }
      return new ResponseEntity<>(new ResultDto(progress, null), HttpStatus.OK);
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found");
  }
}
