package bswe.gamifiedevidencebasednursing.shared.submission.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import bswe.gamifiedevidencebasednursing.domain.Answer;
import bswe.gamifiedevidencebasednursing.domain.Mission;
import bswe.gamifiedevidencebasednursing.domain.OpenQuestionAnswer;
import bswe.gamifiedevidencebasednursing.domain.Question;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.repository.AnswerRepository;
import bswe.gamifiedevidencebasednursing.repository.OpenQuestionAnswerRepository;
import bswe.gamifiedevidencebasednursing.repository.QuestionRepository;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import bswe.gamifiedevidencebasednursing.shared.submission.dto.request.OpenQuestionSubmissionDto;
import bswe.gamifiedevidencebasednursing.shared.submission.dto.request.SubmissionDto;
import bswe.gamifiedevidencebasednursing.shared.submission.dto.response.AdminNotificationDto;
import bswe.gamifiedevidencebasednursing.shared.submission.dto.response.AnswerDetailDto;
import bswe.gamifiedevidencebasednursing.shared.submission.dto.response.ResultDto;
import bswe.gamifiedevidencebasednursing.shared.submission.dto.response.SubmissionResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OpenQuestionSubmissionService {

  private final RoomRepository roomRepository;
  private final QuestionRepository questionRepository;
  private final OpenQuestionAnswerRepository openQuestionAnswerRepository;
  private final AnswerRepository answerRepository;

  public OpenQuestionSubmissionService(RoomRepository roomRepository, QuestionRepository questionRepository, OpenQuestionAnswerRepository openQuestionAnswerRepository, AnswerRepository answerRepository) {
    this.roomRepository = roomRepository;
    this.questionRepository = questionRepository;
    this.openQuestionAnswerRepository = openQuestionAnswerRepository;
    this.answerRepository = answerRepository;
  }

  @Transactional
  public SubmissionResponseDto processSubmission(
      SubmissionDto submissionDto,
      int pointsIfCorrect,
      Consumer<AdminNotificationDto> notificationSender) {

    Room room = roomRepository.findById(submissionDto.getRoomId())
        .orElseThrow(() -> new IllegalArgumentException("Room not found"));

    List<AnswerDetailDto> answerDetails = saveOrUpdateOpenQuestions(room, submissionDto.getOpenQuestions());

    if (!answerDetails.isEmpty() && notificationSender != null) {
      Mission mission = room.getTeam().getMission();
      AdminNotificationDto notification = new AdminNotificationDto(
          mission.getId(),
          mission.getName(),
          room.getId(),
          room.getLocation().getName(),
          Instant.now(),
          answerDetails
      );
      notificationSender.accept(notification);
    }

    int progress = validateLevelOfEvidence(
        submissionDto.getLevelofEvidenceQuestionId(),
        submissionDto.getLevelofEvidenceAnswer(),
        pointsIfCorrect
    );

    room.setProgress(room.getProgress() + progress);
    roomRepository.save(room);

    return new SubmissionResponseDto(room.getProgress(), progress > 0);
  }
  private List<AnswerDetailDto> saveOrUpdateOpenQuestions(Room room, List<OpenQuestionSubmissionDto> openQuestions) {
    if (openQuestions == null || openQuestions.isEmpty()) {
      return List.of();
    }

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

    List<OpenQuestionAnswer> toSave = new ArrayList<>();
    List<AnswerDetailDto> answerDetails = new ArrayList<>();

    for (OpenQuestionSubmissionDto dto : openQuestions) {
      Question question = questions.get(dto.getQuestionId());
      if (question == null) {
        throw new IllegalArgumentException("Question not found");
      }

      OpenQuestionAnswer existing = existingAnswers.get(dto.getQuestionId());
      if (existing != null) {
        existing.setAnswerText(dto.getAnswer());
        if (!existing.isApproved()) {
          answerDetails.add(new AnswerDetailDto(question.getId(), question.getTitle(), dto.getAnswer()));
        }
        toSave.add(existing);
      } else {
        OpenQuestionAnswer newAnswer = new OpenQuestionAnswer();
        newAnswer.setRoom(room);
        newAnswer.setQuestion(question);
        newAnswer.setAnswerText(dto.getAnswer());
        toSave.add(newAnswer);

        answerDetails.add(new AnswerDetailDto(question.getId(), question.getTitle(), dto.getAnswer()));
      }
    }

    openQuestionAnswerRepository.saveAll(toSave);
    return answerDetails;
  }

  public int validateLevelOfEvidence(long questionId, String levelOfEvidenceAnswer, int pointsIfCorrect) {
    Answer answer = answerRepository.findCorrectAnswerByQuestionId(questionId);
    if (answer != null && answer.getText().equalsIgnoreCase(levelOfEvidenceAnswer)) {
      return pointsIfCorrect;
    }
    return 0;
  }

  public ResultDto getResults(long roomId, long missionId) {
    Room room = roomRepository.findById(roomId)
        .orElseThrow(() -> new IllegalArgumentException("Room not found"));

    Mission mission = room.getTeam().getMission();
    if (mission.getId() != missionId) {
      throw new IllegalArgumentException("Mission not found");
    }
    return new ResultDto(room.getProgress(), room.getLocation().getKey());
  }

}
