package bswe.gamifiedevidencebasednursing.feature.admindashboard.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bswe.gamifiedevidencebasednursing.domain.Answer;
import bswe.gamifiedevidencebasednursing.domain.OpenQuestionAnswer;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.feature.admindashboard.dto.request.AnalyticsSubmissionFeedbackDto;
import bswe.gamifiedevidencebasednursing.feature.admindashboard.dto.request.QuestionFeedbackDto;
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


  public ResponseEntity<String> analyticsSubmissionFeedback(AnalyticsSubmissionFeedbackDto analyticsSubmissionFeedbackDto) {
    Optional<Room> optionalRoom = roomRepository.findById(analyticsSubmissionFeedbackDto.getRoomId());
    if (optionalRoom.isEmpty()) {
      throw new IllegalArgumentException("Room not found");
    }
    Room room = optionalRoom.get();
    List<QuestionFeedbackResultDto> results = new ArrayList<>();
    for (QuestionFeedbackDto questionFeedbackDto : analyticsSubmissionFeedbackDto.getQuestions()) {
      if (questionFeedbackDto.getAnswer() == null) {
        throw new IllegalArgumentException("Answer cannot be null");
      }
      if (questionFeedbackDto.isApproved()) {
        room.setProgress(room.getProgress() + 20);
        results.add(new QuestionFeedbackResultDto(questionFeedbackDto.getQuestionId(), true, questionFeedbackDto.getAnswer()));
      } else {
        OpenQuestionAnswer openQuestionAnswer = openQuestionAnswerRepository.findByRoomIdAndQuestionId(room.getId(), questionFeedbackDto.getQuestionId());
        if (openQuestionAnswer == null) {
          throw new IllegalArgumentException("Open question answer not found for room and question");
        }
        openQuestionAnswer.setAnswerText(null);
        openQuestionAnswerRepository.save(openQuestionAnswer);
        results.add(new QuestionFeedbackResultDto(questionFeedbackDto.getQuestionId(), false, null));
      }
    }
    roomRepository.save(room);

    AnalyticsFeedbackDto feedback = new AnalyticsFeedbackDto(
        room.getId(),
        room.getTeam().getMission().getName(),
        room.getProgress(),
        Instant.now(),
        results
    );
    analyticsNotificationService.notifyTeam(feedback);

    return ResponseEntity.ok("Feedback submitted successfully");
  }
}
