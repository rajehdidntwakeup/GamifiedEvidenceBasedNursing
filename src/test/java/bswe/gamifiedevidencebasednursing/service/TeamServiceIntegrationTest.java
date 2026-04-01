package bswe.gamifiedevidencebasednursing.service;

import bswe.gamifiedevidencebasednursing.domain.Game;
import bswe.gamifiedevidencebasednursing.domain.Mission;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.domain.enums.GameStatus;
import bswe.gamifiedevidencebasednursing.feature.gamecreation.service.GameCreationService;
import bswe.gamifiedevidencebasednursing.repository.GameRepository;
import bswe.gamifiedevidencebasednursing.repository.MissionRepository;
import bswe.gamifiedevidencebasednursing.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class TeamServiceIntegrationTest {

    @Autowired
    private GameCreationService gameCreationService;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private bswe.gamifiedevidencebasednursing.repository.QuestionRepository questionRepository;

    @Autowired
    private bswe.gamifiedevidencebasednursing.repository.AnswerRepository answerRepository;

    @BeforeEach
    void setUp() {
        teamRepository.deleteAll();
        answerRepository.deleteAll();
        questionRepository.deleteAll();
        missionRepository.deleteAll();
        gameRepository.deleteAll();
    }

    @Test
    void testCreateTeams_Integration() {
        // Given
        Mission mission1 = new Mission("Mission 1");
        Mission mission2 = new Mission("Mission 2");
        missionRepository.save(mission1);
        missionRepository.save(mission2);

        Game game = new Game("password", GameStatus.CREATED, Instant.now(), Instant.now().plusSeconds(600));
        game = gameRepository.save(game);

        // When
        List<Long> teamIds = gameCreationService.createTeams(game);

        // Then
        assertEquals(2, teamIds.size());

        List<Team> savedTeams = teamRepository.findAll();
        assertEquals(2, savedTeams.size());

        for (Team team : savedTeams) {
            assertEquals(game.getId(), team.getGame().getId());
            assertTrue(team.getMission().getName().startsWith("Mission"));
        }
        
    }
}
