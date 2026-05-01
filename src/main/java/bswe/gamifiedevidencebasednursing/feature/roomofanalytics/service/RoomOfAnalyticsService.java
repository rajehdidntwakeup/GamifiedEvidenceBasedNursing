package bswe.gamifiedevidencebasednursing.feature.roomofanalytics.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bswe.gamifiedevidencebasednursing.domain.Answer;
import bswe.gamifiedevidencebasednursing.domain.Mission;
import bswe.gamifiedevidencebasednursing.domain.OpenQuestionAnswer;
import bswe.gamifiedevidencebasednursing.domain.Question;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.request.OpenQuestionSubmissionDto;
import bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.request.SubmissionDto;
import bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response.AdminNotificationDto;
import bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response.AnswerDetailDto;
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
  public ResponseEntity<String> submitAnalytics(SubmissionDto submissionDto) {
    Optional<Room> roomOptional = roomRepository.findById(submissionDto.getRoomId());
    if (roomOptional.isEmpty()) {
      throw new IllegalArgumentException("Room not found");
    }
    Room room = roomOptional.get();
    List<OpenQuestionAnswer> openQuestionAnswers = new ArrayList<>();
    List<AnswerDetailDto> answerDetails = new ArrayList<>();
    for (OpenQuestionSubmissionDto openQuestionSubmissionDto : submissionDto.getOpenQuestions()) {
      Optional<Question> questionOptional = questionRepository.findById(openQuestionSubmissionDto.getQuestionId());
      if (questionOptional.isEmpty()) {
        throw new IllegalArgumentException("Question not found");
      }
      Question question = questionOptional.get();
      OpenQuestionAnswer openQuestionAnswer = new OpenQuestionAnswer();
      openQuestionAnswer.setRoom(room);
      openQuestionAnswer.setQuestion(question);
      openQuestionAnswer.setAnswerText(openQuestionSubmissionDto.getAnswer());
      openQuestionAnswers.add(openQuestionAnswer);
      AnswerDetailDto answerDetailDto = new AnswerDetailDto(
          question.getId(),
          question.getTitle(),
          openQuestionSubmissionDto.getAnswer()
      );
      answerDetails.add(answerDetailDto);
    }
    List<OpenQuestionAnswer> saved = openQuestionAnswerRepository.saveAll(openQuestionAnswers);
    int progress = validateLevelOfEvidenceAnswer(submissionDto.getLevelofEvidenceQuestionId(),
        submissionDto.getLevelofEvidencAnswer());
    if (progress > 0) {
      room.setProgress(room.getProgress() + progress);
      roomRepository.save(room);
    }

    if (!saved.isEmpty()) {
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

    return ResponseEntity.ok("Analytics submitted successfully");
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
