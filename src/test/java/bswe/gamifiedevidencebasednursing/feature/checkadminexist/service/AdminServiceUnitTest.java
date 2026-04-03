package bswe.gamifiedevidencebasednursing.feature.checkadminexist.service;

import bswe.gamifiedevidencebasednursing.domain.User;
import bswe.gamifiedevidencebasednursing.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class AdminServiceUnitTest {

    private AdminService adminService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        adminService = new AdminService(userRepository);
    }

    @Test
    void isThereAdmin_shouldReturnTrue_whenUsersExist() {
        // Given
        when(userRepository.findAll()).thenReturn(List.of(new User("admin", "pass", "ROLE_ADMIN")));

        // When
        boolean result = adminService.isThereAdmin();

        // Then
        assertTrue(result);
    }

    @Test
    void isThereAdmin_shouldReturnFalse_whenNoUsersExist() {
        // Given
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        boolean result = adminService.isThereAdmin();

        // Then
        assertFalse(result);
    }
}
