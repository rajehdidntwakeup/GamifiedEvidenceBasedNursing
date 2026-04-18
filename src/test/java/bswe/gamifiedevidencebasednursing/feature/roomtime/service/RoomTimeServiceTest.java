package bswe.gamifiedevidencebasednursing.feature.roomtime.service;

import bswe.gamifiedevidencebasednursing.domain.Location;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.feature.roomtime.dto.RoomTimeResponse;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class RoomTimeServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomTimeService roomTimeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void howmuchtimedowehave_shouldReturnCorrectTime() {
        // Given
        long roomId = 1L;
        Room room = new Room();
        room.setStartTime(Instant.now().minusSeconds(60)); // Started 1 minute ago
        room.setExtraTime(5); // 5 minutes extra
        Location location = new Location();
        location.setTimer(10); // 10 minutes original
        room.setLocation(location);
        // Total time = 15 minutes. 1 minute passed. 14 minutes left.

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

        // When
        RoomTimeResponse response = roomTimeService.howmuchtimedowehave(roomId);

        // Then
        assertEquals(13, response.getMinutes()); // 13 or 14 depending on exact 'now' call in service vs test
        // Let's be more precise with 'now' or just check it's around 13-14 minutes
        assertTrue(response.getMinutes() >= 13 && response.getMinutes() <= 14);
    }

    @Test
    void howmuchtimedowehave_shouldReturnZeroIfTimeOut() {
        // Given
        long roomId = 1L;
        Room room = new Room();
        room.setStartTime(Instant.now().minusSeconds(1200)); // Started 20 minutes ago
        room.setExtraTime(0);
        Location location = new Location();
        location.setTimer(10); // 10 minutes total
        room.setLocation(location);

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

        // When
        RoomTimeResponse response = roomTimeService.howmuchtimedowehave(roomId);

        // Then
        assertEquals(0, response.getMinutes());
        assertEquals(0, response.getSeconds());
    }
}
