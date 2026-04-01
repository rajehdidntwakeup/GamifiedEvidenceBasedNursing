package bswe.gamifiedevidencebasednursing.gamecreation.service;

import bswe.gamifiedevidencebasednursing.domain.Question;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.domain.enums.Status;
import bswe.gamifiedevidencebasednursing.feature.gamecreation.service.GameCreationService;
import bswe.gamifiedevidencebasednursing.repository.QuestionRepository;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import bswe.gamifiedevidencebasednursing.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GameCreationServiceIntegrationTest {

    @Autowired
    private GameCreationService gameCreationService;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private bswe.gamifiedevidencebasednursing.repository.AnswerRepository answerRepository;

    @Autowired
    private bswe.gamifiedevidencebasednursing.repository.MissionRepository missionRepository;

    @Autowired
    private bswe.gamifiedevidencebasednursing.repository.GameRepository gameRepository;

    @BeforeEach
    void setUp() {
        roomRepository.deleteAll();
        answerRepository.deleteAll();
        teamRepository.deleteAll();
        gameRepository.deleteAll();
        missionRepository.deleteAll();
        questionRepository.deleteAll();
    }

    @Test
    void createRoomOfKnowledge_shouldCreateRoomsWithTenQuestionsForValidTeams() {
        // Given
        // 1. Create 15 questions
        for (long i = 1; i <= 15; i++) {
            Question q = new Question();
            // Using reflection to set ID as Question does not have a @GeneratedValue
            try {
                java.lang.reflect.Field idField = Question.class.getDeclaredField("id");
                idField.setAccessible(true);
                idField.set(q, i + 100); // Offset to avoid conflict with Flyway questions if any remain
            } catch (Exception e) {
                // Ignore
            }
            q.setTitle("Question " + i);
            questionRepository.save(q);
        }

        // 2. Create 2 teams
        Team team1 = new Team();
        team1.setStatus(Status.READY);
        team1.setWinner(false);
        team1 = teamRepository.save(team1);

        Team team2 = new Team();
        team2.setStatus(Status.READY);
        team2.setWinner(false);
        team2 = teamRepository.save(team2);

        List<Long> teamIds = List.of(team1.getId(), team2.getId());

        // When
        gameCreationService.createRoomOfKnowledge(teamIds);

        // Then
        List<Room> allRooms = roomRepository.findAll();
        assertEquals(2, allRooms.size());

        for (Room room : allRooms) {
            assertNotNull(room.getId());
            assertTrue(teamIds.contains(room.getTeam().getId()));
            assertEquals(10, room.getQuestions().size());
        }
    }
}
