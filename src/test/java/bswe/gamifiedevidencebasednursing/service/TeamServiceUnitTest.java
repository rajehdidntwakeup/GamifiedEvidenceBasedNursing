package bswe.gamifiedevidencebasednursing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import bswe.gamifiedevidencebasednursing.domain.Game;
import bswe.gamifiedevidencebasednursing.domain.Location;
import bswe.gamifiedevidencebasednursing.domain.Mission;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.gamecreation.service.GameCreationService;
import bswe.gamifiedevidencebasednursing.repository.GameRepository;
import bswe.gamifiedevidencebasednursing.repository.MissionRepository;
import bswe.gamifiedevidencebasednursing.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TeamServiceUnitTest {

  @Mock
  private TeamRepository teamRepository;
  @Mock
  private MissionRepository missionRepository;
  @Mock
  private GameRepository gameRepository;

  @InjectMocks
  private GameCreationService gameCreationService;

  private Game game;

  @BeforeEach
  void setUp() {
    game = new Game();
  }

  @Test
  void testCreateTeams_NoMissions_ReturnsEmptyList() {
    // Given
    when(missionRepository.findAll()).thenReturn(Collections.emptyList());

    // When
    List<Long> result = gameCreationService.createTeams(game);

    // Then
    assertTrue(result.isEmpty());
    verify(teamRepository, never()).save(any(Team.class));
  }

  @Test
  void testCreateTeams_SuccessfulCreation() {
    // Given
    Mission mission1 = new Mission("Mission 1");
    Mission mission2 = new Mission("Mission 2");
    Location startLocation = new Location("Start", "start_key", 3600);

    when(missionRepository.findAll()).thenReturn(List.of(mission1, mission2));

    Team team1 = new Team();
    Team team2 = new Team();
    // Since teamRepository.save returns the saved object, we need to mock it to provide IDs
    when(teamRepository.save(any(Team.class))).thenAnswer(invocation -> {
      Team t = invocation.getArgument(0);
      // Assign a dummy ID based on mission name
      if (t.getMission().getName().equals("Mission 1")) {
        // Use reflection or a setter if available, but for now just mock return value
        Team savedTeam = mock(Team.class);
        when(savedTeam.getId()).thenReturn(1L);
        return savedTeam;
      } else {
        Team savedTeam = mock(Team.class);
        when(savedTeam.getId()).thenReturn(2L);
        return savedTeam;
      }
    });

    // When
    List<Long> result = gameCreationService.createTeams(game);

    // Then
    assertEquals(2, result.size());
    assertTrue(result.contains(1L));
    assertTrue(result.contains(2L));

    verify(teamRepository, times(2)).save(any(Team.class));
    verify(missionRepository, times(1)).save(mission1);
    verify(missionRepository, times(1)).save(mission2);

    // Verify relationships
    assertEquals(1, mission1.getTeams().size());
    assertEquals(1, mission2.getTeams().size());
  }
}
