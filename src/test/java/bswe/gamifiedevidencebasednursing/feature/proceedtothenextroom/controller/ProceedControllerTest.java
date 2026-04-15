package bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.controller;

import bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.dto.request.ProceedDto;
import bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.dto.response.RoomResponseDto;
import bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.service.ProceedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProceedControllerTest {

    private ProceedController proceedController;

    @Mock
    private ProceedService proceedService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        proceedController = new ProceedController(proceedService);
    }

    @Test
    void proceedToTheRoomOfAbstracts_shouldReturnOk() {
        ProceedDto proceedDto = new ProceedDto(1L);
        when(proceedService.proceedToTheRoomOfAbstracts(any(ProceedDto.class)))
                .thenReturn(ResponseEntity.ok(new RoomResponseDto()));

        ResponseEntity<RoomResponseDto> response = proceedController.proceedToTheRoomOfAbstracts(proceedDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void proceedToTheRoomOfAnalytics_shouldReturnOk() {
        ProceedDto proceedDto = new ProceedDto(1L);
        when(proceedService.proceedToTheRoomOfAnalytics(any(ProceedDto.class)))
                .thenReturn(ResponseEntity.ok(new RoomResponseDto()));

        ResponseEntity<RoomResponseDto> response = proceedController.proceedToTheRoomOfAnalytics(proceedDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void proceedToTheRoomOfScienceBattle_shouldReturnOk() {
        ProceedDto proceedDto = new ProceedDto(1L);
        when(proceedService.proceedToTheRoomOfScienceBattle(any(ProceedDto.class)))
                .thenReturn(ResponseEntity.ok(new RoomResponseDto()));

        ResponseEntity<RoomResponseDto> response = proceedController.proceedToTheRoomOfScienceBattle(proceedDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
