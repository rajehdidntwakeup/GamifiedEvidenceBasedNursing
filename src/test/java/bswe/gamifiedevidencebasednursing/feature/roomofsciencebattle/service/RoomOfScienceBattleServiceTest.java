package bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.service;

import java.util.List;
import java.util.Optional;

import bswe.gamifiedevidencebasednursing.domain.Answer;
import bswe.gamifiedevidencebasednursing.domain.Location;
import bswe.gamifiedevidencebasednursing.domain.Mission;
import bswe.gamifiedevidencebasednursing.domain.OpenQuestionAnswer;
import bswe.gamifiedevidencebasednursing.domain.Question;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.dto.request.OpenQuestionSubmissionDto;
import bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.dto.request.SubmissionDto;
import bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.dto.response.AdminNotificationDto;
import bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.dto.response.SubmissionResponseDto;
import bswe.gamifiedevidencebasednursing.repository.AnswerRepository;
import bswe.gamifiedevidencebasednursing.repository.OpenQuestionAnswerRepository;
import bswe.gamifiedevidencebasednursing.repository.QuestionRepository;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RoomOfScienceBattleServiceTest {

  @Mock
  private RoomRepository roomRepository;

  @Mock
  private QuestionRepository questionRepository;

  @Mock
  private OpenQuestionAnswerRepository openQuestionAnswerRepository;

  @Mock
  private ScienceBattleNotificationService scienceBattleNotificationService;

  @Mock
  private AnswerRepository answerRepository;

  @InjectMocks
  private RoomOfScienceBattleService roomOfScienceBattleService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void submitScienceBattleEvidence_shouldCorrectlyMatchExistingAnswersByQuestionId() {
    long roomId = 8L;
    Room room = new Room();
    room.setId(roomId);
    room.setProgress(0);
    room.setLocation(new Location("Room of Science Battle", "SB", 30));

    Mission mission = new Mission();
    mission.setId(1L);
    mission.setName("Wundversorgung bei Dekubitus");

    Team team = new Team();
    team.setMission(mission);
    room.setTeam(team);

    Question question120 = new Question();
    question120.setId(120L);
    question120.setTitle("Explain why your chosen study provides better evidence");

    Question question121 = new Question();
    question121.setId(121L);
    question121.setTitle("Compare the key methodological differences");

    // Existing answer has PK id=1L, but belongs to Question 120L
    OpenQuestionAnswer existingAnswer = new OpenQuestionAnswer();
    existingAnswer.setId(1L);
    existingAnswer.setRoom(room);
    existingAnswer.setQuestion(question120);
    existingAnswer.setAnswerText("Old answer for 120");
    existingAnswer.setApproved(false);

    SubmissionDto submissionDto = new SubmissionDto();
    submissionDto.setRoomId(roomId);
    submissionDto.setLevelofEvidenceQuestionId(118L);
    submissionDto.setLevelofEvidenceAnswer("Level II");

    OpenQuestionSubmissionDto openQ1 = new OpenQuestionSubmissionDto();
    openQ1.setQuestionId(120L);
    openQ1.setAnswer("Updated answer for 120");

    OpenQuestionSubmissionDto openQ2 = new OpenQuestionSubmissionDto();
    openQ2.setQuestionId(121L);
    openQ2.setAnswer("New answer for 121");

    submissionDto.setOpenQuestions(List.of(openQ1, openQ2));

    when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
    when(questionRepository.findAllById(List.of(120L, 121L))).thenReturn(List.of(question120, question121));
    when(openQuestionAnswerRepository.findAllByRoomId(roomId)).thenReturn(List.of(existingAnswer));

    Answer correctAnswer = new Answer();
    correctAnswer.setText("Level II");
    when(answerRepository.findCorrectAnswerByQuestionId(118L)).thenReturn(correctAnswer);

    ResponseEntity<SubmissionResponseDto> response = roomOfScienceBattleService.submitScienceBattleEvidence(submissionDto);

    assertNotNull(response);
    assertEquals(200, response.getStatusCode().value());
    assertTrue(response.getBody().levelOfEvidenceApproved());
    assertEquals(10, response.getBody().progress());

    // Verify answers saved
    @SuppressWarnings("unchecked")
    ArgumentCaptor<List<OpenQuestionAnswer>> answersCaptor = ArgumentCaptor.forClass(List.class);
    verify(openQuestionAnswerRepository).saveAll(answersCaptor.capture());
    List<OpenQuestionAnswer> savedAnswers = answersCaptor.getValue();
    assertEquals(2, savedAnswers.size());

    // First answer should be the existing updated entity with id 1
    OpenQuestionAnswer updatedAnswer = savedAnswers.stream()
        .filter(a -> a.getQuestion().getId() == 120L)
        .findFirst().orElseThrow();
    assertEquals(1L, updatedAnswer.getId());
    assertEquals("Updated answer for 120", updatedAnswer.getAnswerText());

    // Second answer should be a newly created entity
    OpenQuestionAnswer newlyCreatedAnswer = savedAnswers.stream()
        .filter(a -> a.getQuestion().getId() == 121L)
        .findFirst().orElseThrow();
    assertEquals("New answer for 121", newlyCreatedAnswer.getAnswerText());

    // Verify admin notification
    ArgumentCaptor<AdminNotificationDto> notificationCaptor = ArgumentCaptor.forClass(AdminNotificationDto.class);
    verify(scienceBattleNotificationService).notifyAdmin(notificationCaptor.capture());
    AdminNotificationDto notification = notificationCaptor.getValue();
    assertEquals(mission.getId(), notification.missionId());
    assertEquals(2, notification.answers().size());
    assertEquals(120L, notification.answers().get(0).questionId());
    assertEquals("Explain why your chosen study provides better evidence", notification.answers().get(0).questionText());
    assertEquals("Updated answer for 120", notification.answers().get(0).answerText());
    assertEquals(121L, notification.answers().get(1).questionId());
    assertEquals("Compare the key methodological differences", notification.answers().get(1).questionText());
    assertEquals("New answer for 121", notification.answers().get(1).answerText());
  }
}
