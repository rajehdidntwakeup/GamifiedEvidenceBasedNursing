package bswe.gamifiedevidencebasednursing.feature.gamecreation.service;

import bswe.gamifiedevidencebasednursing.domain.Location;
import bswe.gamifiedevidencebasednursing.domain.Question;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.domain.enums.Status;
import bswe.gamifiedevidencebasednursing.repository.DocumentRepository;
import bswe.gamifiedevidencebasednursing.repository.LocationRepository;
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

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private LocationRepository locationRepository;

    @BeforeEach
    void setUp() {
        // Delete in correct order: tables with foreign keys first
        roomRepository.deleteAll();
        answerRepository.deleteAll();
        questionRepository.deleteAll();
        documentRepository.deleteAll();
        // mission_question is a join table - delete viaQuestionRepository or skip if no delete method
        teamRepository.deleteAll();
        missionRepository.deleteAll();
        locationRepository.deleteAll();
        gameRepository.deleteAll();
    }

    @Test
    void createRoomOfKnowledge_shouldCreateRoomsWithTenQuestionsForValidTeams() {
        // Given
        // 1. Create "Room of Knowledge" location
        Location location = new Location("Room of Knowledge", "E-C", 10);
        location = locationRepository.save(location);

        // 2. Create 15 questions with the location
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
            q.setLocation(location);
            questionRepository.save(q);
        }

        // 3. Create a game
        bswe.gamifiedevidencebasednursing.domain.Game game = new bswe.gamifiedevidencebasednursing.domain.Game("password", bswe.gamifiedevidencebasednursing.domain.enums.GameStatus.CREATED);
        game = gameRepository.save(game);

        // 4. Create 2 teams
        Team team1 = new Team();
        team1.setStatus(Status.READY);
        team1.setWinner(false);
        team1.setGame(game);
        team1 = teamRepository.save(team1);

        Team team2 = new Team();
        team2.setStatus(Status.READY);
        team2.setWinner(false);
        team2.setGame(game);
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
