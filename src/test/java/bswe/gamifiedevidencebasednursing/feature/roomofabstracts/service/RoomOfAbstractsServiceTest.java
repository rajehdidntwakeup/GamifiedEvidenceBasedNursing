package bswe.gamifiedevidencebasednursing.feature.roomofabstracts.service;

import bswe.gamifiedevidencebasednursing.domain.Answer;
import bswe.gamifiedevidencebasednursing.domain.Location;
import bswe.gamifiedevidencebasednursing.domain.Question;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.feature.roomofabstracts.dto.request.QuestionAnswerDto;
import bswe.gamifiedevidencebasednursing.feature.roomofabstracts.dto.request.VerifyRoomOfAbstractsAnswersDto;
import bswe.gamifiedevidencebasednursing.feature.roomofabstracts.dto.response.ResultDto;
import bswe.gamifiedevidencebasednursing.repository.QuestionRepository;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RoomOfAbstractsServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomOfAbstractsService roomOfAbstractsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void verifyRoomOfAbstractsAnswers_shouldReturnResultWithProgress_whenSomeAnswersAreCorrect() {
        // Given
        long roomId = 1L;
        Room room = new Room();
        room.setProgress(0);
        room.setLocation(createLocation("Room of Abstracts", "F-O", 20));

        QuestionAnswerDto answer1 = new QuestionAnswerDto(1L, 1L); // Correct
        QuestionAnswerDto answer2 = new QuestionAnswerDto(2L, 3L); // Wrong
        VerifyRoomOfAbstractsAnswersDto dto = new VerifyRoomOfAbstractsAnswersDto(roomId, 1L, List.of(answer1, answer2));

        Question question1 = new Question();
        Answer correctAnswer1 = createAnswer(1L, true);
        question1.setAnswers(java.util.Set.of(correctAnswer1));

        Question question2 = new Question();
        Answer wrongAnswer2 = createAnswer(3L, false);
        question2.setAnswers(java.util.Set.of(wrongAnswer2));

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question1));
        when(questionRepository.findById(2L)).thenReturn(Optional.of(question2));

        // When
        ResultDto result = roomOfAbstractsService.verifyRoomOfAbstractsAnswers(dto);

        // Then
        assertEquals(8, result.getProgress()); // 1 correct out of 2 = 8%
        assertNull(result.getKey());
        verify(roomRepository, times(1)).findById(roomId);
        verify(roomRepository, times(1)).save(any(Room.class));
    }

    @Test
    void verifyRoomOfAbstractsAnswers_shouldReturnResultWith100ProgressAndKey_whenAllAnswersAreCorrect() {
        // Given
        long roomId = 1L;
        Room room = new Room();
        room.setProgress(0);
        room.setLocation(createLocation("Room of Abstracts", "F-O", 20));

        // 12 correct answers
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

        VerifyRoomOfAbstractsAnswersDto dto = new VerifyRoomOfAbstractsAnswersDto(roomId, 1L, answers);

        // Create 12 questions with correct answers
        for (int i = 1; i <= 12; i++) {
            Question question = new Question();
            question.setAnswers(java.util.Set.of(createAnswer((long) i, true)));
            when(questionRepository.findById((long) i)).thenReturn(Optional.of(question));
        }

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

        // When
        ResultDto result = roomOfAbstractsService.verifyRoomOfAbstractsAnswers(dto);

        // Then
        assertEquals(100, result.getProgress());
        assertEquals("F-O", result.getKey());
        verify(roomRepository, times(1)).save(any(Room.class));
    }

    @Test
    void verifyRoomOfAbstractsAnswers_shouldThrowException_whenRoomNotFound() {
        // Given
        long roomId = 1L;
        VerifyRoomOfAbstractsAnswersDto dto = new VerifyRoomOfAbstractsAnswersDto(roomId, 1L, List.of());

        when(roomRepository.findById(roomId)).thenReturn(Optional.empty());

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            roomOfAbstractsService.verifyRoomOfAbstractsAnswers(dto)
        );

        assertEquals("Room not found", exception.getMessage());
    }

    @Test
    void verifyRoomOfAbstractsAnswers_shouldReturnCurrentProgress_whenRoomAlreadyHasProgress() {
        // Given
        long roomId = 1L;
        Room room = new Room();
        room.setProgress(50); // Already 50% complete
        room.setLocation(createLocation("Room of Abstracts", "F-O", 20));

        QuestionAnswerDto answer = new QuestionAnswerDto(1L, 1L); // 1 correct answer = 8%
        VerifyRoomOfAbstractsAnswersDto dto = new VerifyRoomOfAbstractsAnswersDto(roomId, 1L, List.of(answer));

        Question question = new Question();
        question.setAnswers(java.util.Set.of(createAnswer(1L, true)));

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

        // When
        ResultDto result = roomOfAbstractsService.verifyRoomOfAbstractsAnswers(dto);

        // Then
        assertEquals(58, result.getProgress()); // 50 + 8 = 58
    }

    @Test
    void verifyRoomOfAbstractsAnswers_shouldCapProgressAt100() {
        // Given
        long roomId = 1L;
        Room room = new Room();
        room.setProgress(95); // Already 95% complete
        room.setLocation(createLocation("Room of Abstracts", "F-O", 20));

        QuestionAnswerDto answer = new QuestionAnswerDto(1L, 1L);
        VerifyRoomOfAbstractsAnswersDto dto = new VerifyRoomOfAbstractsAnswersDto(roomId, 1L, List.of(answer));

        Question question = new Question();
        question.setAnswers(java.util.Set.of(createAnswer(1L, true)));

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

        // When
        ResultDto result = roomOfAbstractsService.verifyRoomOfAbstractsAnswers(dto);

        // Then
        assertEquals(100, result.getProgress()); // Capped at 100, not 103
    }

    @Test
    void verifyRoomOfAbstractsAnswers_shouldHandleMissingQuestion() {
        // Given
        long roomId = 1L;
        Room room = new Room();
        room.setProgress(0);
        room.setLocation(createLocation("Room of Abstracts", "F-O", 20));

        QuestionAnswerDto answer = new QuestionAnswerDto(1L, 1L);
        VerifyRoomOfAbstractsAnswersDto dto = new VerifyRoomOfAbstractsAnswersDto(roomId, 1L, List.of(answer));

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
        when(questionRepository.findById(1L)).thenReturn(Optional.empty());

        // When
        ResultDto result = roomOfAbstractsService.verifyRoomOfAbstractsAnswers(dto);

        // Then
        assertEquals(0, result.getProgress()); // No progress added if question not found
    }

    // Helper methods
    private Answer createAnswer(long id, boolean isCorrect) {
        Answer answer = new Answer();
        answer.setId(id);
        try {
            Field correctField = Answer.class.getDeclaredField("isCorrect");
            correctField.setAccessible(true);
            correctField.set(answer, isCorrect);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return answer;
    }

    private Location createLocation(String name, String key, int timer) {
        Location location = new Location();
        try {
            Field nameField = Location.class.getDeclaredField("name");
            nameField.setAccessible(true);
            nameField.set(location, name);

            Field keyField = Location.class.getDeclaredField("key");
            keyField.setAccessible(true);
            keyField.set(location, key);

            Field timerField = Location.class.getDeclaredField("timer");
            timerField.setAccessible(true);
            timerField.set(location, timer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return location;
    }
}
