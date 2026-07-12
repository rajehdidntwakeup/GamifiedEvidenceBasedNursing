package bswe.gamifiedevidencebasednursing.feature.adminfeedbacksubmission.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bswe.gamifiedevidencebasednursing.domain.OpenQuestionAnswer;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.feature.adminfeedbacksubmission.dto.request.SubmissionFeedbackDto;
import bswe.gamifiedevidencebasednursing.feature.adminfeedbacksubmission.dto.request.QuestionFeedbackDto;
import bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response.AnalyticsFeedbackDto;
import bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response.QuestionFeedbackResultDto;
import bswe.gamifiedevidencebasednursing.feature.roomofanalytics.service.AnalyticsNotificationService;
import bswe.gamifiedevidencebasednursing.repository.OpenQuestionAnswerRepository;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SubmissionService {

  private final RoomRepository roomRepository;
  private final OpenQuestionAnswerRepository openQuestionAnswerRepository;
  private final AnalyticsNotificationService analyticsNotificationService;

  public SubmissionService(RoomRepository roomRepository,
                           OpenQuestionAnswerRepository openQuestionAnswerRepository,
                           AnalyticsNotificationService analyticsNotificationService) {
    this.roomRepository = roomRepository;
    this.openQuestionAnswerRepository = openQuestionAnswerRepository;
    this.analyticsNotificationService = analyticsNotificationService;
  }


  public ResponseEntity<String> analyticsSubmissionFeedback(SubmissionFeedbackDto submissionFeedbackDto) {
    Optional<Room> optionalRoom = roomRepository.findById(submissionFeedbackDto.getRoomId());
    if (optionalRoom.isEmpty()) {
      throw new IllegalArgumentException("Room not found");
    }
    Room room = optionalRoom.get();
    List<OpenQuestionAnswer> allOpenQuestionAnswers = openQuestionAnswerRepository.findAllByRoomId(room.getId());
    List<QuestionFeedbackResultDto> results = new ArrayList<>();
    for (QuestionFeedbackDto questionFeedbackDto : submissionFeedbackDto.getQuestions()) {
      if (questionFeedbackDto.getAnswer() == null) {
        throw new IllegalArgumentException("Answer cannot be null");
      }
      OpenQuestionAnswer openQuestionAnswer = openQuestionAnswerRepository.findByRoomIdAndQuestionId(room.getId(), questionFeedbackDto.getQuestionId());
      if (questionFeedbackDto.isApproved()) {
        openQuestionAnswer.setApproved(true);
        room.setProgress(room.getProgress() + 20);
        results.add(new QuestionFeedbackResultDto(questionFeedbackDto.getQuestionId(), true, questionFeedbackDto.getAnswer()));
      } else {
        if (openQuestionAnswer == null) {
          throw new IllegalArgumentException("Open question answer not found for room and question");
        }
        openQuestionAnswer.setApproved(false);
        openQuestionAnswer.setAnswerText(null);
        results.add(new QuestionFeedbackResultDto(questionFeedbackDto.getQuestionId(), false, null));
      }
      openQuestionAnswerRepository.save(openQuestionAnswer);
    }


    if (!allOpenQuestionAnswers.isEmpty()) {
      for (OpenQuestionAnswer openQuestionAnswer : allOpenQuestionAnswers) {
        if (openQuestionAnswer.isApproved()) {
          results.add(new QuestionFeedbackResultDto(openQuestionAnswer.getQuestion().getId(), openQuestionAnswer.isApproved(), openQuestionAnswer.getAnswerText()));
        }
      }
    }

    roomRepository.save(room);

    AnalyticsFeedbackDto feedback = new AnalyticsFeedbackDto(
        room.getId(),
        room.getProgress(),
        room.getTeam().getMission().getName(),
        Instant.now(),
        results
    );
    analyticsNotificationService.notifyTeam(feedback);

    return ResponseEntity.ok("Feedback submitted successfully");
  }

  public void sciencebattleSubmissionFeedback(SubmissionFeedbackDto submissionFeedbackDto) {

  }

  private List<OpenQuestionAnswer> processAdminFeedbackSubmission(SubmissionFeedbackDto submissionFeedbackDto) {
    Optional<Room> optionalRoom = roomRepository.findById(submissionFeedbackDto.getRoomId());
    if (optionalRoom.isEmpty()) {
      throw new IllegalArgumentException("Room not found");
    }
    Room room = optionalRoom.get();
    List<OpenQuestionAnswer> allOpenQuestionAnswers = openQuestionAnswerRepository.findAllByRoomId(room.getId());
    List<QuestionFeedbackResultDto> results = new ArrayList<>();
    for (QuestionFeedbackDto questionFeedbackDto : submissionFeedbackDto.getQuestions()) {
      if (questionFeedbackDto.getAnswer() == null) {
        throw new IllegalArgumentException("Answer cannot be null");
      }
      OpenQuestionAnswer openQuestionAnswer = openQuestionAnswerRepository.findByRoomIdAndQuestionId(room.getId(), questionFeedbackDto.getQuestionId());
      if (questionFeedbackDto.isApproved()) {
        openQuestionAnswer.setApproved(true);
        room.setProgress(room.getProgress() + 20);
        results.add(new QuestionFeedbackResultDto(questionFeedbackDto.getQuestionId(), true, questionFeedbackDto.getAnswer()));
      } else {
        if (openQuestionAnswer == null) {
          throw new IllegalArgumentException("Open question answer not found for room and question");
        }
        openQuestionAnswer.setApproved(false);
        openQuestionAnswer.setAnswerText(null);
        results.add(new QuestionFeedbackResultDto(questionFeedbackDto.getQuestionId(), false, null));
      }
      openQuestionAnswerRepository.save(openQuestionAnswer);
    }
    return allOpenQuestionAnswers;
  }
}
