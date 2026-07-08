package bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import bswe.gamifiedevidencebasednursing.domain.Answer;
import bswe.gamifiedevidencebasednursing.domain.Mission;
import bswe.gamifiedevidencebasednursing.domain.OpenQuestionAnswer;
import bswe.gamifiedevidencebasednursing.domain.Question;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.dto.request.OpenQuestionSubmissionDto;
import bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.dto.request.SubmissionDto;
import bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.dto.response.AdminNotificationDto;
import bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.dto.response.AnswerDetailDto;
import bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.dto.response.ResultDto;
import bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.dto.response.SubmissionResponseDto;
import bswe.gamifiedevidencebasednursing.repository.AnswerRepository;
import bswe.gamifiedevidencebasednursing.repository.OpenQuestionAnswerRepository;
import bswe.gamifiedevidencebasednursing.repository.QuestionRepository;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomOfScienceBattleService {

  private final RoomRepository roomRepository;
  private final QuestionRepository questionRepository;
  private final OpenQuestionAnswerRepository openQuestionAnswerRepository;
  private final ScienceBattleNotificationService  scienceBattleNotificationService;
  private final AnswerRepository answerRepository;

  public RoomOfScienceBattleService(RoomRepository roomRepository, QuestionRepository questionRepository,
                                    OpenQuestionAnswerRepository openQuestionAnswerRepository,
                                    ScienceBattleNotificationService scienceBattleNotificationService,
                                    AnswerRepository answerRepository) {
    this.roomRepository = roomRepository;
    this.questionRepository = questionRepository;
    this.openQuestionAnswerRepository = openQuestionAnswerRepository;
    this.scienceBattleNotificationService = scienceBattleNotificationService;
    this.answerRepository = answerRepository;
  }

  @Transactional
  public ResponseEntity<SubmissionResponseDto> submitScienceBattleEvidence(SubmissionDto submissionDto) {
    Room room = roomRepository.findById(submissionDto.getRoomId())
        .orElseThrow(() -> new IllegalArgumentException("Room not found"));

    List<OpenQuestionAnswer> toSave = new ArrayList<>();
    List<AnswerDetailDto> answerDetails = new ArrayList<>();
    List<OpenQuestionSubmissionDto> openQuestions = submissionDto.getOpenQuestions();

    if (openQuestions != null && !openQuestions.isEmpty()) {
      List<Long> questionIds = openQuestions.stream()
          .map(OpenQuestionSubmissionDto::getQuestionId)
          .toList();

      Map<Long, Question> questionMap = questionRepository
          .findAllById(questionIds).stream()
          .collect(Collectors.toMap(Question::getId, Function.identity()));

      Map<Long, OpenQuestionAnswer> existingAnswers = openQuestionAnswerRepository
          .findAllByRoomId(room.getId())
          .stream()
          .collect(Collectors.toMap(OpenQuestionAnswer::getId, Function.identity()));

      for (OpenQuestionSubmissionDto dto : openQuestions) {
        Question question = questionMap.get((Long) dto.getQuestionId());
        if (question == null) throw new IllegalArgumentException("Question not found");

        OpenQuestionAnswer existingAnswer = existingAnswers.get((Long) dto.getQuestionId());
        if (existingAnswer != null) {
          existingAnswer.setAnswerText(dto.getAnswer());
          if (!existingAnswer.isApproved()) {
            answerDetails.add(new AnswerDetailDto(
                question.getId(),
                question.getTitle(),
                dto.getAnswer()
            ));
          }
          toSave.add(existingAnswer);
        } else {
          OpenQuestionAnswer newAnswer = new OpenQuestionAnswer();
          newAnswer.setRoom(room);
          newAnswer.setQuestion(question);
          newAnswer.setAnswerText(dto.getAnswer());
          toSave.add(newAnswer);

          answerDetails.add(new AnswerDetailDto(
              question.getId(),
              question.getTitle(),
              dto.getAnswer()
          ));
        }
      }
      openQuestionAnswerRepository.saveAll(toSave);
    }

    if (!answerDetails.isEmpty()) {
      Mission mission = room.getTeam().getMission();
      AdminNotificationDto notificationDto = new AdminNotificationDto(
          mission.getId(),
          mission.getName(),
          room.getId(),
          room.getLocation().getName(),
          Instant.now(),
          answerDetails
      );
      scienceBattleNotificationService.notifyAdmin(notificationDto);
    }

    int progress = validateLevelOfEvidenceAnswer(submissionDto.getLevelofEvidenceQuestionId(),
        submissionDto.getLevelofEvidencAnswer());

    room.setProgress(room.getProgress() + progress);
    roomRepository.save(room);
    return ResponseEntity.ok(new SubmissionResponseDto(room.getProgress(), progress > 0));
  }
  
  public ResponseEntity<ResultDto> getResults(long roomId, long missionId) {
    Optional<Room> room = roomRepository.findById(roomId);
    if (room.isPresent()) {
      Mission mission = room.get().getTeam().getMission();
      if (mission.getId() == missionId) {
        return ResponseEntity.ok(new ResultDto(room.get().getProgress(), room.get().getLocation().getKey()));
      }
      throw new IllegalArgumentException("Mission not found");
    }
    throw new IllegalArgumentException("Room not found");
  }

  private int validateLevelOfEvidenceAnswer(long questionId, String levelOfEvidenceAnswer) {
    Answer answer = answerRepository.findCorrectAnswerByQuestionId(questionId);
    if (answer == null) {
      return 0;
    }
    if (answer.getText().equalsIgnoreCase(levelOfEvidenceAnswer)) {
      return 20;
    }
    return 0;
  }
}
