package bswe.gamifiedevidencebasednursing.feature.checkadminexist.service;

import bswe.gamifiedevidencebasednursing.domain.User;
import bswe.gamifiedevidencebasednursing.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class AdminServiceIntegrationTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void isThereAdmin_shouldReturnTrue_whenUsersAreInDatabase() {
        // Given
        User user = new User("admin", "password", "ROLE_ADMIN");
        userRepository.save(user);

        // When
        boolean result = adminService.isThereAdmin();

        // Then
        assertTrue(result);
    }

    @Test
    void isThereAdmin_shouldReturnFalse_whenDatabaseIsEmpty() {
        // When
        boolean result = adminService.isThereAdmin();

        // Then
        assertFalse(result);
    }
}
