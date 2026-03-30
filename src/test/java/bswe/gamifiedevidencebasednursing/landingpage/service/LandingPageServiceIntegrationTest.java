package bswe.gamifiedevidencebasednursing.landingpage.service;

import bswe.gamifiedevidencebasednursing.domain.Game;
import bswe.gamifiedevidencebasednursing.domain.enums.GameStatus;
import bswe.gamifiedevidencebasednursing.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class LandingPageServiceIntegrationTest {

    @Autowired
    private LandingPageService landingPageService;

    @Autowired
    private GameRepository gameRepository;

    @BeforeEach
    void setUp() {
        gameRepository.deleteAll();
    }

    @Test
    void isThereAnyGameRunning_shouldReturnTrue_whenGameStatusIsCreated() {
        // Given
        Game game = new Game("password", GameStatus.CREATED, null, null);
        gameRepository.save(game);

        // When
        boolean result = landingPageService.isThereAnyGameRunning();

        // Then
        assertTrue(result);
    }

    @Test
    void isThereAnyGameRunning_shouldReturnTrue_whenGameStatusIsRunning() {
        // Given
        Game game = new Game("password", GameStatus.RUNNING, null, null);
        gameRepository.save(game);

        // When
        boolean result = landingPageService.isThereAnyGameRunning();

        // Then
        assertTrue(result);
    }

    @Test
    void isThereAnyGameRunning_shouldReturnFalse_whenNoGameExists() {
        // When
        boolean result = landingPageService.isThereAnyGameRunning();

        // Then
        assertFalse(result);
    }

    @Test
    void isThereAnyGameRunning_shouldReturnFalse_whenGameStatusIsFinished() {
        // Given
        Game game = new Game("password", GameStatus.FINISHED, null, null);
        gameRepository.save(game);

        // When
        boolean result = landingPageService.isThereAnyGameRunning();

        // Then
        assertFalse(result);
    }
}
