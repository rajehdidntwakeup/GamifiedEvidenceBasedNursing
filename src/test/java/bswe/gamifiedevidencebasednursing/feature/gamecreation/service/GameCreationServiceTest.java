package bswe.gamifiedevidencebasednursing.feature.gamecreation.service;

import bswe.gamifiedevidencebasednursing.domain.Game;
import bswe.gamifiedevidencebasednursing.domain.Location;
import bswe.gamifiedevidencebasednursing.domain.Mission;
import bswe.gamifiedevidencebasednursing.domain.Question;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.feature.gamecreation.dto.response.GameResponseDto;
import bswe.gamifiedevidencebasednursing.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GameCreationServiceTest {

    @Mock
    private GameRepository gameRepository;
    @Mock
    private TeamRepository teamRepository;
    @Mock
    private MissionRepository missionRepository;
    @Mock
    private LocationRepository locationRepository;
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private GameCreationService gameCreationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createGame_shouldCreateGameTeamsAndRooms() {
        // Given
        Mission mission = new Mission("Mission 1");
        mission.setId(1L);
        when(missionRepository.findAll()).thenReturn(List.of(mission));

        Location location = new Location("Room of Knowledge", "E-C", 10);
        when(locationRepository.findByName("Room of Knowledge")).thenReturn(Optional.of(location));

        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            questions.add(new Question());
        }
        when(questionRepository.findQuestionsForRoomOfKnowledge()).thenReturn(questions);

        when(gameRepository.save(any())).thenAnswer(i -> {
            Game g = i.getArgument(0);
            java.lang.reflect.Field idField = Game.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(g, 1L);
            return g;
        });

        when(teamRepository.saveAll(any())).thenAnswer(i -> {
            List<Team> ts = i.getArgument(0);
            for (long id = 1; id <= ts.size(); id++) {
                java.lang.reflect.Field idField = Team.class.getDeclaredField("id");
                idField.setAccessible(true);
                idField.set(ts.get((int)id-1), id);
            }
            return ts;
        });

        // When
        GameResponseDto response = gameCreationService.createGame();

        // Then
        assertEquals(1L, response.getGameId());
        assertEquals(1, response.getTeamPasswords().size());
        verify(teamRepository, times(1)).saveAll(any());
        verify(roomRepository, times(1)).saveAll(any());
    }
}
