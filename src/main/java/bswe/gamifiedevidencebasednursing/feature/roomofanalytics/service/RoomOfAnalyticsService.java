package bswe.gamifiedevidencebasednursing.feature.roomofanalytics.service;

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
import bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.request.OpenQuestionSubmissionDto;
import bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.request.SubmissionDto;
import bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response.AdminNotificationDto;
import bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response.AnswerDetailDto;
import bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response.ResultDto;
import bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response.SubmissionResponseDto;
import bswe.gamifiedevidencebasednursing.repository.AnswerRepository;
import bswe.gamifiedevidencebasednursing.repository.OpenQuestionAnswerRepository;
import bswe.gamifiedevidencebasednursing.repository.QuestionRepository;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomOfAnalyticsService {

  private final RoomRepository roomRepository;
  private final QuestionRepository questionRepository;
  private final OpenQuestionAnswerRepository openQuestionAnswerRepository;
  private final AnalyticsNotificationService analyticsNotificationService;
  private final AnswerRepository answerRepository;

  public RoomOfAnalyticsService(RoomRepository roomRepository, QuestionRepository questionRepository,
                                OpenQuestionAnswerRepository openQuestionAnswerRepository,
                                AnalyticsNotificationService analyticsNotificationService,
                                AnswerRepository answerRepository) {
    this.roomRepository = roomRepository;
    this.questionRepository = questionRepository;
    this.openQuestionAnswerRepository = openQuestionAnswerRepository;
    this.analyticsNotificationService = analyticsNotificationService;
    this.answerRepository = answerRepository;
  }


  @Transactional
  public ResponseEntity<SubmissionResponseDto> submitAnalytics(SubmissionDto submissionDto) {
    Room room = roomRepository.findById(submissionDto.getRoomId())
        .orElseThrow(() -> new IllegalArgumentException("Room not found"));

    List<OpenQuestionAnswer> toSave = new ArrayList<>();
    List<AnswerDetailDto> answerDetails = new ArrayList<>();
    List<OpenQuestionSubmissionDto> openQuestions = submissionDto.getOpenQuestions();

    if (openQuestions != null && !openQuestions.isEmpty()) {
      List<Long> questionIds = openQuestions.stream()
          .map(OpenQuestionSubmissionDto::getQuestionId)
          .toList();

      Map<Long, Question> questions = questionRepository.findAllById(questionIds)
          .stream()
          .collect(Collectors.toMap(Question::getId, Function.identity()));

      Map<Long, OpenQuestionAnswer> existingAnswers = openQuestionAnswerRepository
          .findAllByRoomId(room.getId())
          .stream()
          .collect(Collectors.toMap(
              oqa -> oqa.getQuestion().getId(),
              Function.identity()
          ));

      for (OpenQuestionSubmissionDto dto : openQuestions) {
        Question question = questions.get(dto.getQuestionId());
        if (question == null) {
          throw new IllegalArgumentException("Question not found");
        }

        OpenQuestionAnswer existing = existingAnswers.get(dto.getQuestionId());
        if (existing != null) {
          existing.setAnswerText(dto.getAnswer());
          if (!existing.isApproved()) {
            answerDetails.add(new AnswerDetailDto(
                question.getId(),
                question.getTitle(),
                dto.getAnswer()
            ));
          }

          toSave.add(existing);
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
      AdminNotificationDto notification = new AdminNotificationDto(
          mission.getId(),
          mission.getName(),
          room.getId(),
          room.getLocation().getName(),
          Instant.now(),
          answerDetails
      );
      analyticsNotificationService.notifyAdmin(notification);
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
    }
    throw new IllegalArgumentException("Room not found");
  }

  private int validateLevelOfEvidenceAnswer(long questionId, String levelOfEvidenceAnswer) {
    Answer answer = answerRepository.findCorrectAnswerByQuestionId(questionId);
    if (answer == null) {
      throw new IllegalArgumentException("Answer not found");
    }
    if (answer.getText().equalsIgnoreCase(levelOfEvidenceAnswer)) {
      return 20;
    }
    return 0;
  }
}
