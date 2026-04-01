package bswe.gamifiedevidencebasednursing.gamecreation.service;

import bswe.gamifiedevidencebasednursing.domain.Question;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.feature.gamecreation.service.GameCreationService;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @Spy
    private GameCreationService gameCreationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createRoomOfKnowledge_shouldCreateRoomsForAllTeams() {
        // Given
        List<Long> teamIds = List.of(1L, 2L);
        Team team1 = new Team();
        team1.setId(1L);
        Team team2 = new Team();
        team2.setId(2L);

        when(teamRepository.findById(1L)).thenReturn(Optional.of(team1));
        when(teamRepository.findById(2L)).thenReturn(Optional.of(team2));

        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            questions.add(new Question());
        }
        when(questionRepository.findAll()).thenReturn(questions);

        when(roomRepository.save(any(Room.class))).thenAnswer(invocation -> {
            Room room = invocation.getArgument(0);
            // Simulate saving by setting an ID
            java.lang.reflect.Field idField = Room.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(room, 1L); 
            return room;
        });

        // When
        gameCreationService.createRoomOfKnowledge(teamIds);

        // Then
        verify(teamRepository, times(1)).findById(1L);
        verify(teamRepository, times(1)).findById(2L);
        verify(roomRepository, times(2)).save(any(Room.class));
    }

    @Test
    void createRoomOfKnowledge_shouldThrowException_whenTeamNotFound() {
        // Given
        List<Long> teamIds = List.of(1L);
        when(teamRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> gameCreationService.createRoomOfKnowledge(teamIds));
    }

    @Test
    void createRoomOfKnowledge_shouldThrowException_whenRoomSaveFails() {
        // Given
        List<Long> teamIds = List.of(1L);
        Team team = new Team();
        team.setId(1L);
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));
        
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            questions.add(new Question());
        }
        when(questionRepository.findAll()).thenReturn(questions);

        // Room save returns room without ID
        when(roomRepository.save(any(Room.class))).thenReturn(new Room());

        // When & Then
        assertThrows(IllegalStateException.class, () -> gameCreationService.createRoomOfKnowledge(teamIds));
    }
}
