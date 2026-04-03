package bswe.gamifiedevidencebasednursing.feature.roomofabstracts.controller;

import bswe.gamifiedevidencebasednursing.feature.roomofabstracts.dto.request.QuestionAnswerDto;
import bswe.gamifiedevidencebasednursing.feature.roomofabstracts.dto.request.VerifyRoomOfAbstractsAnswersDto;
import bswe.gamifiedevidencebasednursing.feature.roomofabstracts.dto.response.ResultDto;
import bswe.gamifiedevidencebasednursing.feature.roomofabstracts.service.RoomOfAbstractsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class RoomOfAbstractsControllerIntegrationTest {

    @Autowired
    private RoomOfAbstractsController roomOfAbstractsController;

    @MockitoBean
    private RoomOfAbstractsService roomOfAbstractsService;

    @BeforeEach
    void setUp() {
        // Mock the service to return a known result
        ResultDto mockResult = new ResultDto(50, null);
        doReturn(mockResult).when(roomOfAbstractsService).verifyRoomOfAbstractsAnswers(any());
    }

    @Test
    void verifyRoomOfAbstractsAnswers_shouldReturnOkWithResultDto() {
        // Given
        VerifyRoomOfAbstractsAnswersDto dto = new VerifyRoomOfAbstractsAnswersDto(1L, 1L, List.of());

        // When
        ResponseEntity<ResultDto> response = roomOfAbstractsController.verifyRoomOfAbstractsAnswers(dto);

        // Then
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(50, response.getBody().getProgress());
    }

    @Test
    void verifyRoomOfAbstractsAnswers_shouldCallServiceWithCorrectDto() {
        // Given
        List<QuestionAnswerDto> answers = List.of(new QuestionAnswerDto(1L, 1L));
        VerifyRoomOfAbstractsAnswersDto dto = new VerifyRoomOfAbstractsAnswersDto(1L, 1L, answers);

        // When
        roomOfAbstractsController.verifyRoomOfAbstractsAnswers(dto);

        // Then
        ArgumentCaptor<VerifyRoomOfAbstractsAnswersDto> captor = ArgumentCaptor.forClass(VerifyRoomOfAbstractsAnswersDto.class);
        verify(roomOfAbstractsService).verifyRoomOfAbstractsAnswers(captor.capture());
        VerifyRoomOfAbstractsAnswersDto capturedDto = captor.getValue();
        assertEquals(1L, capturedDto.getRoomId());
        assertEquals(1L, capturedDto.getMissionId());
        assertEquals(1, capturedDto.getAnswers().size());
    }

    @Test
    void verifyRoomOfAbstractsAnswers_shouldHandleEmptyAnswers() {
        // Given
        VerifyRoomOfAbstractsAnswersDto dto = new VerifyRoomOfAbstractsAnswersDto(1L, 1L, List.of());

        // When
        ResponseEntity<ResultDto> response = roomOfAbstractsController.verifyRoomOfAbstractsAnswers(dto);

        // Then
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }
}
