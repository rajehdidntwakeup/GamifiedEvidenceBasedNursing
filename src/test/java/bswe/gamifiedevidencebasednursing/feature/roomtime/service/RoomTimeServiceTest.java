package bswe.gamifiedevidencebasednursing.feature.roomtime.service;

import bswe.gamifiedevidencebasednursing.domain.Location;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.feature.roomtime.dto.RoomTimeResponse;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RoomTimeServiceTest {

    @Mock
    private RoomRepository roomRepository;

    private Clock clock;
    private RoomTimeService roomTimeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clock = Clock.fixed(Instant.parse("2026-04-19T19:20:00Z"), ZoneId.of("UTC"));
        roomTimeService = new RoomTimeService(roomRepository, clock);
    }

    @Test
    void howMuchTimeDoWeHave_shouldReturnCorrectTime() {
        // Given
        long roomId = 1L;
        Room room = new Room();
        room.setStartTime(Instant.now(clock).minusSeconds(60)); // Started 1 minute ago
        room.setExtraTime(5); // 5 minutes extra
        Location location = new Location();
        location.setTimer(10); // 10 minutes original
        room.setLocation(location);
        // Total time = 15 minutes. 1 minute passed. 14 minutes left.

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

        // When
        RoomTimeResponse response = roomTimeService.howMuchTimeDoWeHave(roomId);

        // Then
        assertEquals(14, response.getMinutes());
        assertEquals(0, response.getSeconds());
    }

    @Test
    void howMuchTimeDoWeHave_shouldReturnZeroIfTimeOut() {
        // Given
        long roomId = 1L;
        Room room = new Room();
        room.setStartTime(Instant.now(clock).minusSeconds(1200)); // Started 20 minutes ago
        room.setExtraTime(0);
        Location location = new Location();
        location.setTimer(10); // 10 minutes total
        room.setLocation(location);

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

        // When
        RoomTimeResponse response = roomTimeService.howMuchTimeDoWeHave(roomId);

        // Then
        assertEquals(0, response.getMinutes());
        assertEquals(0, response.getSeconds());
    }
}
