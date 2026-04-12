package bswe.gamifiedevidencebasednursing.feature.roomofabstracts.service;

import bswe.gamifiedevidencebasednursing.domain.Answer;
import bswe.gamifiedevidencebasednursing.domain.Game;
import bswe.gamifiedevidencebasednursing.domain.Location;
import bswe.gamifiedevidencebasednursing.domain.Question;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.domain.enums.Status;
import bswe.gamifiedevidencebasednursing.domain.enums.GameStatus;
import bswe.gamifiedevidencebasednursing.feature.roomofabstracts.dto.request.QuestionAnswerDto;
import bswe.gamifiedevidencebasednursing.feature.roomofabstracts.dto.request.VerifyRoomOfAbstractsAnswersDto;
import bswe.gamifiedevidencebasednursing.feature.roomofabstracts.dto.response.ResultDto;
import bswe.gamifiedevidencebasednursing.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RoomOfAbstractsServiceIntegrationTest {

    @Autowired
    private RoomOfAbstractsService roomOfAbstractsService;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private DocumentRepository documentRepository;

    private Long questionIdCounter = 1L;

    @BeforeEach
    void setUp() {
        // Delete in correct order
        answerRepository.deleteAll();
        questionRepository.deleteAll();
        roomRepository.deleteAll();
        teamRepository.deleteAll();
        gameRepository.deleteAll();
        missionRepository.deleteAll();
        locationRepository.deleteAll();
        documentRepository.deleteAll();
        // Reset ID counter for each test
        questionIdCounter = 1L;
    }

    @Test
    void verifyRoomOfAbstractsAnswers_shouldVerifyAnswersAndReturnProgressForValidRoom() {
        // Given
        Location location = createLocation("Room of Abstracts", "F-O", 20);
        location = locationRepository.save(location);

        Game game = createGame("password");
        game = gameRepository.save(game);

        Team team = createTeam(game);
        team = teamRepository.save(team);

        // Create 12 questions with answers (use unique IDs)
        for (long i = 1; i <= 12; i++) {
            Question question = createQuestion(questionIdCounter++, location);
            question = questionRepository.save(question);

            Answer answer = createAnswer(null, true, question);
            answer = answerRepository.save(answer);
            question.getAnswers().add(answer);
        }

        // Create room with 0 progress
        Room room = createRoom(team, location, 0);
        room = roomRepository.save(room);

        // Get the saved questions and answers
        List<Question> questions = questionRepository.findAll();
        List<Answer> answers = answerRepository.findAll();

        // Verify answers - send 3 correct answers (first 3)
        List<QuestionAnswerDto> questionAnswers = List.of(
            new QuestionAnswerDto(questions.get(0).getId(), answers.get(0).getId()),
            new QuestionAnswerDto(questions.get(1).getId(), answers.get(1).getId()),
            new QuestionAnswerDto(questions.get(2).getId(), answers.get(2).getId())
        );

        VerifyRoomOfAbstractsAnswersDto dto = new VerifyRoomOfAbstractsAnswersDto(room.getId(), 1L, questionAnswers);

        // When
        ResultDto result = roomOfAbstractsService.verifyRoomOfAbstractsAnswers(dto);

        // Then
        // Progress should increase by 8.33% per correct answer (100/12 = 8.33%)
        // Integer math: 3 * 100 / 12 = 25
        assertEquals(25, result.getProgress());
        assertNull(result.getKey());
    }

    @Test
    void verifyRoomOfAbstractsAnswers_shouldCompleteRoomAndReturnKey_whenAll12AnswersAreCorrect() {
        // Given
        Location location = createLocation("Room of Abstracts", "SECRET-KEY", 20);
        location = locationRepository.save(location);

        Game game = createGame("password");
        game = gameRepository.save(game);

        Team team = createTeam(game);
        team = teamRepository.save(team);

        // Create 12 questions with correct answers (use unique IDs)
        for (long i = 1; i <= 12; i++) {
            Question question = createQuestion(questionIdCounter++, location);
            question = questionRepository.save(question);

            Answer answer = createAnswer(null, true, question);
            answer = answerRepository.save(answer);
            question.getAnswers().add(answer);
        }

        // Create room with 0 progress
        Room room = createRoom(team, location, 0);
        room = roomRepository.save(room);

        // Get the saved questions and answers
        List<Question> questions = questionRepository.findAll();
        List<Answer> answers = answerRepository.findAll();

        // Send 12 correct answers (all of them)
        List<QuestionAnswerDto> questionAnswers = List.of(
            new QuestionAnswerDto(questions.get(0).getId(), answers.get(0).getId()),
            new QuestionAnswerDto(questions.get(1).getId(), answers.get(1).getId()),
            new QuestionAnswerDto(questions.get(2).getId(), answers.get(2).getId()),
            new QuestionAnswerDto(questions.get(3).getId(), answers.get(3).getId()),
            new QuestionAnswerDto(questions.get(4).getId(), answers.get(4).getId()),
            new QuestionAnswerDto(questions.get(5).getId(), answers.get(5).getId()),
            new QuestionAnswerDto(questions.get(6).getId(), answers.get(6).getId()),
            new QuestionAnswerDto(questions.get(7).getId(), answers.get(7).getId()),
            new QuestionAnswerDto(questions.get(8).getId(), answers.get(8).getId()),
            new QuestionAnswerDto(questions.get(9).getId(), answers.get(9).getId()),
            new QuestionAnswerDto(questions.get(10).getId(), answers.get(10).getId()),
            new QuestionAnswerDto(questions.get(11).getId(), answers.get(11).getId())
        );

        VerifyRoomOfAbstractsAnswersDto dto = new VerifyRoomOfAbstractsAnswersDto(room.getId(), 1L, questionAnswers);

        // When
        ResultDto result = roomOfAbstractsService.verifyRoomOfAbstractsAnswers(dto);

        // Then
        assertEquals(100, result.getProgress());
        assertEquals("SECRET-KEY", result.getKey());
    }

    @Test
    void verifyRoomOfAbstractsAnswers_shouldNotAddProgressForWrongAnswers() {
        // Given
        Location location = createLocation("Room of Abstracts", "F-O", 20);
        location = locationRepository.save(location);

        Game game = createGame("password");
        game = gameRepository.save(game);

        Team team = createTeam(game);
        team = teamRepository.save(team);

        // Create 12 questions with correct and wrong answers (use unique IDs)
        for (long i = 1; i <= 12; i++) {
            Question question = createQuestion(questionIdCounter++, location);
            question = questionRepository.save(question);

            // Wrong answer (first)
            Answer wrongAnswer = createAnswer(null, false, question);
            wrongAnswer = answerRepository.save(wrongAnswer);
            question.getAnswers().add(wrongAnswer);

            // Correct answer (second)
            Answer correctAnswer = createAnswer(null, true, question);
            correctAnswer = answerRepository.save(correctAnswer);
            question.getAnswers().add(correctAnswer);
        }

        // Create room with 0 progress
        Room room = createRoom(team, location, 0);
        room = roomRepository.save(room);

        // Get the saved questions and wrong answers (first answer of each question is wrong)
        List<Question> questions = questionRepository.findAll();
        List<Answer> wrongAnswers = answerRepository.findAll().stream()
            .filter(a -> !a.isCorrect())
            .toList();

        // Send wrong answers
        List<QuestionAnswerDto> questionAnswers = List.of(
            new QuestionAnswerDto(questions.get(0).getId(), wrongAnswers.get(0).getId()),
            new QuestionAnswerDto(questions.get(1).getId(), wrongAnswers.get(1).getId()),
            new QuestionAnswerDto(questions.get(2).getId(), wrongAnswers.get(2).getId())
        );

        VerifyRoomOfAbstractsAnswersDto dto = new VerifyRoomOfAbstractsAnswersDto(room.getId(), 1L, questionAnswers);

        // When
        ResultDto result = roomOfAbstractsService.verifyRoomOfAbstractsAnswers(dto);

        // Then
        assertEquals(0, result.getProgress()); // No progress for wrong answers
    }

    @Test
    void verifyRoomOfAbstractsAnswers_shouldReturnKeyOnlyWhen100Progress() {
        // Given
        Location location = createLocation("Room of Abstracts", "END-KEY", 20);
        location = locationRepository.save(location);

        Game game = createGame("password");
        game = gameRepository.save(game);

        Team team = createTeam(game);
        team = teamRepository.save(team);

        // Create 12 questions (use unique IDs)
        for (long i = 1; i <= 12; i++) {
            Question question = createQuestion(questionIdCounter++, location);
            question = questionRepository.save(question);

            Answer answer = createAnswer(null, true, question);
            answer = answerRepository.save(answer);
            question.getAnswers().add(answer);
        }

        // Create room with 95 progress
        Room room = createRoom(team, location, 95);
        room = roomRepository.save(room);

        // Get the first question and its answer
        Question question = questionRepository.findAll().get(0);
        Answer answer = answerRepository.findAll().get(0);

        // Send 1 correct answer (should reach 100 with 100/12 = 8% = 95+8=103 -> capped at 100)
        List<QuestionAnswerDto> questionAnswers = List.of(new QuestionAnswerDto(question.getId(), answer.getId()));
        VerifyRoomOfAbstractsAnswersDto dto = new VerifyRoomOfAbstractsAnswersDto(room.getId(), 1L, questionAnswers);

        // When
        ResultDto result = roomOfAbstractsService.verifyRoomOfAbstractsAnswers(dto);

        // Then
        assertEquals(100, result.getProgress());
        assertEquals("END-KEY", result.getKey());
    }

    @Test
    void verifyRoomOfAbstractsAnswers_shouldHandleEmptyAnswersList() {
        // Given
        Location location = createLocation("Room of Abstracts", "F-O", 20);
        location = locationRepository.save(location);

        Game game = createGame("password");
        game = gameRepository.save(game);

        Team team = createTeam(game);
        team = teamRepository.save(team);

        Room room = createRoom(team, location, 50);
        room = roomRepository.save(room);

        VerifyRoomOfAbstractsAnswersDto dto = new VerifyRoomOfAbstractsAnswersDto(room.getId(), 1L, List.of());

        // When
        ResultDto result = roomOfAbstractsService.verifyRoomOfAbstractsAnswers(dto);

        // Then
        assertEquals(50, result.getProgress()); // No change
        assertNull(result.getKey());
    }

    // Helper methods
    private Location createLocation(String name, String key, int timer) {
        return new Location(name, key, timer);
    }

    private Game createGame(String password) {
        return new Game(password, GameStatus.CREATED);
    }

    private Team createTeam(Game game) {
        Team team = new Team();
        team.setGame(game);
        team.setStatus(Status.READY);
        team.setWinner(false);
        return team;
    }

    private Question createQuestion(Long id, Location location) {
        Question question = new Question();
        question.setId(id);
        question.setLocation(location);
        question.setTitle("Question " + id);
        return question;
    }

    private Answer createAnswer(Long id, boolean isCorrect, Question question) {
        Answer answer = new Answer();
        if (id != null) {
            answer.setId(id);
        }
        answer.setCorrect(isCorrect);
        answer.setQuestion(question);
        return answer;
    }

    private Room createRoom(Team team, Location location, int progress) {
        Room room = new Room();
        room.setTeam(team);
        room.setLocation(location);
        room.setProgress(progress);
        return room;
    }
}
