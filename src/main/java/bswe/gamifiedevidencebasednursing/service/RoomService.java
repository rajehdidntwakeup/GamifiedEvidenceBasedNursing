package bswe.gamifiedevidencebasednursing.service;

import bswe.gamifiedevidencebasednursing.domain.Answer;
import bswe.gamifiedevidencebasednursing.domain.Game;
import bswe.gamifiedevidencebasednursing.domain.Question;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.domain.dto.QuestionDto;
import bswe.gamifiedevidencebasednursing.domain.dto.QuestionDto.AnswerDto;
import bswe.gamifiedevidencebasednursing.domain.dto.request.SubmitAnswerRequest;
import bswe.gamifiedevidencebasednursing.domain.dto.response.RoomOfKnowledgeAnswerDto;
import bswe.gamifiedevidencebasednursing.domain.dto.response.RoomOfKnowledgeQuestionDto;
import bswe.gamifiedevidencebasednursing.domain.dto.response.RoomStatusResponse;
import bswe.gamifiedevidencebasednursing.domain.dto.response.SubmitAnswerResponse;
import bswe.gamifiedevidencebasednursing.domain.enums.Location;
import bswe.gamifiedevidencebasednursing.domain.enums.Mission;
import bswe.gamifiedevidencebasednursing.domain.enums.Status;
import bswe.gamifiedevidencebasednursing.repository.GameRepository;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import bswe.gamifiedevidencebasednursing.repository.TeamRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static bswe.gamifiedevidencebasednursing.domain.enums.Status.READY;

@Service
public class RoomService {

  private final RoomRepository roomRepository;
  private final QuestionService questionService;
  private final GameRepository gameRepository;
  private final TeamRepository teamRepository;

  public RoomService(RoomRepository roomRepository, QuestionService questionService, GameRepository gameRepository, TeamRepository teamRepository) {
    this.roomRepository = roomRepository;
    this.questionService = questionService;
    this.gameRepository = gameRepository;
    this.teamRepository = teamRepository;
  }

  public void createRoomOfKnowledge(Team team) {
    Room room = new Room(Location.ROOM_OF_KNOWLEDGE, READY, 600); // 10 minutes = 600 seconds
    List<Question> questionList = questionService.getRoomOfKnowledgeQuestionList();
    room.setQuestions(new HashSet<>(questionList));
    room.setTeam(team);
    room = roomRepository.save(room);
    if (room.getId() == null) {
      throw new IllegalStateException("Failed to create room");
    }
  }

  public List<RoomOfKnowledgeQuestionDto> getRoomOfKnowledgeQuestionList(long gameId, Mission mission, String password) {
    Game game = gameRepository.findById(gameId).orElseThrow(() -> new IllegalArgumentException("Game not found"));
    if (game.getPassword() != null && !game.getPassword().equals(password)) {
      throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Invalid password");
    }

    Room room = roomRepository.findRoomByGameIdAndMission(gameId, mission);
    if (room != null) {
      if (room.getQuestions() != null || room.getQuestions().isEmpty()) {
        return getRoomOfKnowledgeQuestionDtos(room);
      }
    }
    return null;
  }

  /**
   * Get remaining timer for a team's current room.
   *
   * @param team the team
   * @return remaining seconds, or null if no active room
   */
  public Integer getRoomTimer(Team team) {
    return team.getRoomList().stream()
        .filter(room -> room.getStatus() != null && !room.getStatus().name().equals("FINISHED"))
        .findFirst()
        .map(Room::getTimer)
        .orElse(null);
  }

  /**
   * Get room status with questions for a team.
   *
   * @param roomId the room ID
   * @param teamId the team ID
   * @return room status response
   */
  @Transactional(readOnly = true)
  public RoomStatusResponse getRoomStatus(Long roomId, Long teamId) {
    Room room = roomRepository.findById(roomId)
        .orElseThrow(() -> new IllegalArgumentException("Room not found"));

    // Verify team belongs to this room
    if (!room.getTeam().getId().equals(teamId)) {
      throw new IllegalArgumentException("Team does not have access to this room");
    }

    List<QuestionDto> questionDtos = room.getQuestions().stream()
        .map(this::toQuestionDto)
        .toList();

    // Count answered questions (this would require tracking in a real implementation)
    int answeredCount = 0; // TODO: Track answered questions

    return new RoomStatusResponse(
        room.getId(),
        room.getLocation(),
        room.getStatus(),
        room.getTimer(),
        room.getQuestions().size(),
        answeredCount,
        questionDtos,
        room.getStatus() == Status.FINISHED
    );
  }

  /**
   * Get questions for a room.
   *
   * @param roomId the room ID
   * @param teamId the team ID
   * @return list of questions
   */
  @Transactional(readOnly = true)
  public List<QuestionDto> getQuestionsForRoom(Long roomId, Long teamId) {
    Room room = roomRepository.findById(roomId)
        .orElseThrow(() -> new IllegalArgumentException("Room not found"));

    if (!room.getTeam().getId().equals(teamId)) {
      throw new IllegalArgumentException("Team does not have access to this room");
    }

    return room.getQuestions().stream()
        .map(this::toQuestionDto)
        .toList();
  }

  /**
   * Submit an answer for a question.
   *
   * @param roomId  the room ID
   * @param request the answer submission request
   * @return submission result
   */
  @Transactional
  public SubmitAnswerResponse submitAnswer(Long roomId, SubmitAnswerRequest request) {
    Room room = roomRepository.findById(roomId)
        .orElseThrow(() -> new IllegalArgumentException("Room not found"));

    if (!room.getTeam().getId().equals(request.teamId())) {
      throw new IllegalArgumentException("Team does not have access to this room");
    }

    Question question = room.getQuestions().stream()
        .filter(q -> q.getId().equals(request.questionId()))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Question not found in this room"));

    Answer selectedAnswer = question.getAnswers().stream()
        .filter(a -> a.getId().equals(request.answerId()))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Answer not found"));

    boolean isCorrect = selectedAnswer.isCorrect();
    int pointsEarned = isCorrect ? calculatePoints(room.getTimer()) : 0;

    // Update team score (simplified - would need proper score tracking)
    Team team = room.getTeam();
    int newTotalScore = calculateTeamScore(team) + pointsEarned;

    // Check if room is complete
    boolean roomCompleted = checkRoomCompletion(room);
    if (roomCompleted) {
      room.setStatus(Status.FINISHED);
      roomRepository.save(room);
    }

    Long nextQuestionId = findNextQuestion(room, request.questionId());

    String feedback = isCorrect
        ? "Correct! Well done!"
        : "Incorrect. The correct answer was: " + getCorrectAnswerText(question);

    return new SubmitAnswerResponse(
        isCorrect,
        pointsEarned,
        newTotalScore,
        feedback,
        roomCompleted,
        nextQuestionId
    );
  }

  /**
   * Get a hint for a question (costs points).
   *
   * @param roomId     the room ID
   * @param teamId     the team ID
   * @param questionId the question ID
   * @return hint text
   */
  @Transactional(readOnly = true)
  public String getHint(Long roomId, Long teamId, Long questionId) {
    Room room = roomRepository.findById(roomId)
        .orElseThrow(() -> new IllegalArgumentException("Room not found"));

    if (!room.getTeam().getId().equals(teamId)) {
      throw new IllegalArgumentException("Team does not have access to this room");
    }

    // In a real implementation, hints would be stored with questions
    // For now, return a generic hint
    return "Think about the PICO framework when analyzing this question.";
  }

  /**
   * Skip a question.
   *
   * @param roomId     the room ID
   * @param teamId     the team ID
   * @param questionId the question ID
   * @return updated room status
   */
  @Transactional
  public RoomStatusResponse skipQuestion(Long roomId, Long teamId, Long questionId) {
    Room room = roomRepository.findById(roomId)
        .orElseThrow(() -> new IllegalArgumentException("Room not found"));

    if (!room.getTeam().getId().equals(teamId)) {
      throw new IllegalArgumentException("Team does not have access to this room");
    }

    // Mark question as skipped (would need tracking in real implementation)
    // For now, just return updated status
    return getRoomStatus(roomId, teamId);
  }

  // Helper methods


  private static @NonNull List<RoomOfKnowledgeQuestionDto> getRoomOfKnowledgeQuestionDtos(Room room) {
    List<RoomOfKnowledgeQuestionDto> roomOfKnowledgeQuestionDtos = new ArrayList<>();
    for (Question question : room.getQuestions()) {
      List<RoomOfKnowledgeAnswerDto> answers = new ArrayList<>();
      for (Answer answer : question.getAnswers()) {
        RoomOfKnowledgeAnswerDto roomOfKnowledgeAnswerDto = new RoomOfKnowledgeAnswerDto(answer.getText(), answer.isCorrect());
        answers.add(roomOfKnowledgeAnswerDto);
      }
      RoomOfKnowledgeQuestionDto roomOfKnowledgeQuestionDto = new RoomOfKnowledgeQuestionDto(question.getTitle(), answers);
      roomOfKnowledgeQuestionDtos.add(roomOfKnowledgeQuestionDto);
    }
    return roomOfKnowledgeQuestionDtos;
  }

  private QuestionDto toQuestionDto(Question question) {
    List<AnswerDto> answerDtos = question.getAnswers().stream()
        .map(a -> new AnswerDto(a.getId(), a.getText()))
        .collect(Collectors.toList());

    return new QuestionDto(
        question.getId(),
        question.getTitle(),
        question.getImage(),
        answerDtos
    );
  }

  private int calculatePoints(int timerRemaining) {
    // Base points + time bonus
    int basePoints = 100;
    int timeBonus = timerRemaining / 10; // 1 point per 10 seconds remaining
    return basePoints + timeBonus;
  }

  private int calculateTeamScore(Team team) {
    // TODO: Implement proper score tracking
    return 0;
  }

  private boolean checkRoomCompletion(Room room) {
    // TODO: Check if all questions answered
    return false;
  }

  private Long findNextQuestion(Room room, Long currentQuestionId) {
    // Find next unanswered question
    Set<Question> questions = room.getQuestions();
    return questions.stream()
        .map(Question::getId)
        .filter(id -> !id.equals(currentQuestionId))
        .findFirst()
        .orElse(null);
  }

  private String getCorrectAnswerText(Question question) {
    return question.getAnswers().stream()
        .filter(Answer::isCorrect)
        .findFirst()
        .map(Answer::getText)
        .orElse("Unknown");
  }
}
