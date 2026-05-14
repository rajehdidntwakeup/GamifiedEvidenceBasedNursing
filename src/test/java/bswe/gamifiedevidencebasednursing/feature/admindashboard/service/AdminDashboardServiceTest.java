package bswe.gamifiedevidencebasednursing.feature.admindashboard.service;

import bswe.gamifiedevidencebasednursing.domain.Game;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.repository.GameRepository;
import bswe.gamifiedevidencebasednursing.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminDashboardServiceTest {

    @Mock
    private GameRepository gameRepository;
    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private AdminDashboardService adminDashboardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void closeGameSession_shouldDetermineWinnerCorrectly() {
        // Given
        Game game = new Game();
        try {
            java.lang.reflect.Field idField = Game.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(game, 1L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        when(gameRepository.findCreatedOrRunningGame()).thenReturn(Optional.of(game));

        Team team1 = new Team();
        team1.setId(1L);
        Team team2 = new Team();
        team2.setId(2L);
        List<Team> teams = Arrays.asList(team1, team2);
        when(teamRepository.findByGameIdWithRooms(1L)).thenReturn(teams);

        Room room1_1 = new Room();
        room1_1.setStartTime(java.time.Instant.now());
        Room room1_2 = new Room();
        team1.setRoomList(Arrays.asList(room1_1, room1_2));

        Room room2_1 = new Room();
        team2.setRoomList(Collections.singletonList(room2_1));

        // When
        adminDashboardService.closeGameSession();

        // Then
        assertTrue(team1.isWinner());
        assertFalse(team2.isWinner());
        verify(teamRepository).saveAll(any());
    }

    @Test
    void closeGameSession_shouldHandleMultipleWinners() {
        // Given
        Game game = new Game();
        try {
            java.lang.reflect.Field idField = Game.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(game, 1L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        when(gameRepository.findCreatedOrRunningGame()).thenReturn(Optional.of(game));

        Team team1 = new Team();
        team1.setId(1L);
        Team team2 = new Team();
        team2.setId(2L);
        List<Team> teams = Arrays.asList(team1, team2);
        when(teamRepository.findByGameIdWithRooms(1L)).thenReturn(teams);

        Room room1_1 = new Room();
        room1_1.setStartTime(java.time.Instant.now());
        team1.setRoomList(Collections.singletonList(room1_1));

        Room room2_1 = new Room();
        room2_1.setStartTime(java.time.Instant.now());
        team2.setRoomList(Collections.singletonList(room2_1));

        // When
        adminDashboardService.closeGameSession();

        // Then
        assertTrue(team1.isWinner());
        assertTrue(team2.isWinner());
        verify(teamRepository).saveAll(any());
    }

    @Test
    void closeGameSession_shouldDeleteGameIfAllRoomsNotStarted() {
        // Given
        Game game = new Game();
        try {
            java.lang.reflect.Field idField = Game.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(game, 1L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        when(gameRepository.findCreatedOrRunningGame()).thenReturn(Optional.of(game));

        Team team1 = new Team();
        team1.setId(1L);
        Room room1 = new Room();
        room1.setStartTime(null);
        room1.setEndTime(null);
        team1.setRoomList(Collections.singletonList(room1));

        List<Team> teams = Collections.singletonList(team1);
        when(teamRepository.findByGameIdWithRooms(1L)).thenReturn(teams);

        // When
        adminDashboardService.closeGameSession();

        // Then
        verify(gameRepository).delete(game);
        verify(gameRepository, never()).save(any());
    }
}
