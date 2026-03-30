package bswe.gamifiedevidencebasednursing.landingpage.service;

import bswe.gamifiedevidencebasednursing.domain.Game;
import bswe.gamifiedevidencebasednursing.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class LandingPageServiceTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private LandingPageService landingPageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void isThereAnyGameRunning_shouldReturnTrue_whenGameIsPresent() {
        // Given
        when(gameRepository.findCreatedOrRunningGame()).thenReturn(Optional.of(new Game()));

        // When
        boolean result = landingPageService.isThereAnyGameRunning();

        // Then
        assertTrue(result);
    }

    @Test
    void isThereAnyGameRunning_shouldReturnFalse_whenNoGameIsPresent() {
        // Given
        when(gameRepository.findCreatedOrRunningGame()).thenReturn(Optional.empty());

        // When
        boolean result = landingPageService.isThereAnyGameRunning();

        // Then
        assertFalse(result);
    }
}
