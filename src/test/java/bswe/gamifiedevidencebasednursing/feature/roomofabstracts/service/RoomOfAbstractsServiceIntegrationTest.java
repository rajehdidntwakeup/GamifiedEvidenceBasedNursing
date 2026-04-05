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
    private ImageRepository imageRepository;

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
        imageRepository.deleteAll();
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

        // Create 12 questions with answers
        for (long i = 1; i <= 12; i++) {
            Question question = createQuestion(i, location);
            question = questionRepository.save(question);

            Answer answer = createAnswer(i, true, question);
            question.getAnswers().add(answer);
            answerRepository.save(answer);
        }

        // Create room with 0 progress
        Room room = createRoom(team, location, 0);
        room = roomRepository.save(room);

        // Verify answers - send 3 correct answers
        List<QuestionAnswerDto> answers = List.of(
            new QuestionAnswerDto(1L, 1L),
            new QuestionAnswerDto(2L, 2L),
            new QuestionAnswerDto(3L, 3L)
        );

        VerifyRoomOfAbstractsAnswersDto dto = new VerifyRoomOfAbstractsAnswersDto(room.getId(), 1L, answers);

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

        // Create 12 questions with correct answers
        for (long i = 1; i <= 12; i++) {
            Question question = createQuestion(i, location);
            question = questionRepository.save(question);

            Answer answer = createAnswer(i, true, question);
            question.getAnswers().add(answer);
            answerRepository.save(answer);
        }

        // Create room with 0 progress
        Room room = createRoom(team, location, 0);
        room = roomRepository.save(room);

        // Send 12 correct answers
        List<QuestionAnswerDto> answers = List.of(
            new QuestionAnswerDto(1L, 1L),
            new QuestionAnswerDto(2L, 2L),
            new QuestionAnswerDto(3L, 3L),
            new QuestionAnswerDto(4L, 4L),
            new QuestionAnswerDto(5L, 5L),
            new QuestionAnswerDto(6L, 6L),
            new QuestionAnswerDto(7L, 7L),
            new QuestionAnswerDto(8L, 8L),
            new QuestionAnswerDto(9L, 9L),
            new QuestionAnswerDto(10L, 10L),
            new QuestionAnswerDto(11L, 11L),
            new QuestionAnswerDto(12L, 12L)
        );

        VerifyRoomOfAbstractsAnswersDto dto = new VerifyRoomOfAbstractsAnswersDto(room.getId(), 1L, answers);

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

        // Create 12 questions with correct answer at ID 100, wrong at ID 1
        for (long i = 1; i <= 12; i++) {
            Question question = createQuestion(i, location);
            question = questionRepository.save(question);

            // Wrong answer at ID 1
            Answer wrongAnswer = createAnswer(1L, false, question);
            question.getAnswers().add(wrongAnswer);
            answerRepository.save(wrongAnswer);

            // Correct answer at ID 100
            Answer correctAnswer = createAnswer(100L, true, question);
            question.getAnswers().add(correctAnswer);
            answerRepository.save(correctAnswer);
        }

        // Create room with 0 progress
        Room room = createRoom(team, location, 0);
        room = roomRepository.save(room);

        // Send wrong answers (IDs 1)
        List<QuestionAnswerDto> wrongAnswers = List.of(
            new QuestionAnswerDto(1L, 1L),
            new QuestionAnswerDto(2L, 1L),
            new QuestionAnswerDto(3L, 1L)
        );

        VerifyRoomOfAbstractsAnswersDto dto = new VerifyRoomOfAbstractsAnswersDto(room.getId(), 1L, wrongAnswers);

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

        // Create 12 questions
        for (long i = 1; i <= 12; i++) {
            Question question = createQuestion(i, location);
            question = questionRepository.save(question);

            Answer answer = createAnswer(100L, true, question);
            question.getAnswers().add(answer);
            answerRepository.save(answer);
        }

        // Create room with 95 progress
        Room room = createRoom(team, location, 95);
        room = roomRepository.save(room);

        // Send 1 correct answer (should reach 100 with 100/12 = 8% = 95+8=103 -> capped at 100)
        List<QuestionAnswerDto> answers = List.of(new QuestionAnswerDto(1L, 100L));
        VerifyRoomOfAbstractsAnswersDto dto = new VerifyRoomOfAbstractsAnswersDto(room.getId(), 1L, answers);

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

    private Question createQuestion(long id, Location location) {
        Question question = new Question();
        question.setId(id);
        question.setLocation(location);
        return question;
    }

    private Answer createAnswer(long id, boolean isCorrect, Question question) {
        Answer answer = new Answer();
        answer.setId(id);
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
