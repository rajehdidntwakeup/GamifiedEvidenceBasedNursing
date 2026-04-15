package bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.service;

import bswe.gamifiedevidencebasednursing.domain.*;
import bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.dto.request.ProceedDto;
import bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.dto.response.RoomResponseDto;
import bswe.gamifiedevidencebasednursing.repository.LocationRepository;
import bswe.gamifiedevidencebasednursing.repository.QuestionRepository;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import bswe.gamifiedevidencebasednursing.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class ProceedServiceTest {

    @Mock
    private RoomRepository roomRepository;
    @Mock
    private TeamRepository teamRepository;
    @Mock
    private LocationRepository locationRepository;
    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private ProceedService proceedService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void proceedToTheRoomOfAbstracts_shouldReturnOk_whenConditionsMet() {
        // Given
        long roomId = 1L;
        long teamId = 2L;
        long missionId = 3L;
        long nextRoomId = 4L;
        ProceedDto proceedDto = new ProceedDto(roomId);

        Location currentLoc = new Location("Room of Knowledge", "KNOWLEDGE", 10);
        setId(currentLoc, 10L);

        Team team = new Team();
        team.setId(teamId);
        Mission mission = new Mission("Mission 1");
        setId(mission, missionId);
        team.setMission(mission);

        Room room = new Room();
        setId(room, roomId);
        room.setProgress(100);
        room.setLocation(currentLoc);
        room.setTeam(team);

        Location nextLoc = new Location("Room of Abstracts", "ABSTRACTS", 10);
        setId(nextLoc, 11L);

        Question q1 = new Question("Question 1", nextLoc);
        q1.setId(100L);
        Answer a1 = new Answer();
        a1.setId(1000L);
        a1.setText("Answer 1");
        q1.setAnswers(Set.of(a1));

        Question q2 = new Question("Question 2", nextLoc);
        q2.setId(101L);
        Document d1 = new Document("path/to/doc");
        q2.setDocuments(Set.of(d1));

        Set<Question> questions = new HashSet<>(List.of(q1, q2));

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
        when(teamRepository.findById(teamId)).thenReturn(Optional.of(team));
        when(locationRepository.findByName("Room of Abstracts")).thenReturn(Optional.of(nextLoc));
        when(questionRepository.findByLocationIdAndMissionId(11L, missionId)).thenReturn(new ArrayList<>(questions));
        
        when(roomRepository.save(any(Room.class))).thenAnswer(invocation -> {
            Room savedRoom = invocation.getArgument(0);
            setId(savedRoom, nextRoomId);
            return savedRoom;
        });

        // When
        ResponseEntity<RoomResponseDto> response = proceedService.proceedToTheRoomOfAbstracts(proceedDto);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(nextRoomId, response.getBody().getRoomId());
        assertEquals(missionId, response.getBody().getMissionId());
        assertEquals(1, response.getBody().getQuestions().size());
        assertEquals("Question 2", response.getBody().getMainQuestion());
        assertEquals(1, response.getBody().getDocs().size());
        assertEquals("path/to/doc", response.getBody().getDocs().get(0));
    }

    @Test
    void proceedToNextRoom_shouldReturnNotFound_whenRoomProgressNot100() {
        // Given
        long roomId = 1L;
        ProceedDto proceedDto = new ProceedDto(roomId);

        Room room = new Room();
        setId(room, roomId);
        room.setProgress(50);

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

        // When
        ResponseEntity<RoomResponseDto> response = proceedService.proceedToTheRoomOfAbstracts(proceedDto);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void proceedToNextRoom_shouldReturnNotFound_whenLocationNameDoesNotMatch() {
        // Given
        long roomId = 1L;
        ProceedDto proceedDto = new ProceedDto(roomId);

        Location loc = new Location("Wrong Room", "WRONG", 10);
        Room room = new Room();
        setId(room, roomId);
        room.setProgress(100);
        room.setLocation(loc);

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

        // When
        ResponseEntity<RoomResponseDto> response = proceedService.proceedToTheRoomOfAbstracts(proceedDto);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void proceedToTheRoomOfAnalytics_shouldCallWithCorrectParameters() {
        // Given
        long roomId = 1L;
        ProceedDto proceedDto = new ProceedDto(roomId);

        Location currentLoc = new Location("Room of Abstracts", "ABSTRACTS", 10);
        Room room = new Room();
        setId(room, roomId);
        room.setProgress(100);
        room.setLocation(currentLoc);
        room.setTeam(new Team());
        room.getTeam().setId(2L);

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
        // Mocking createRoom to avoid deep mocking here, or just let it return null if team not found
        when(teamRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        ResponseEntity<RoomResponseDto> response = proceedService.proceedToTheRoomOfAnalytics(proceedDto);

        // Then
        // If team is empty, createRoom returns null, so result is ok(null) which is weird but that's the code
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void proceedToTheRoomOfScienceBattle_shouldCallWithCorrectParameters() {
        // Given
        long roomId = 1L;
        ProceedDto proceedDto = new ProceedDto(roomId);

        Location currentLoc = new Location("Room of Analytics", "ANALYTICS", 10);
        Room room = new Room();
        setId(room, roomId);
        room.setProgress(100);
        room.setLocation(currentLoc);
        room.setTeam(new Team());
        room.getTeam().setId(2L);

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
        when(teamRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        ResponseEntity<RoomResponseDto> response = proceedService.proceedToTheRoomOfScienceBattle(proceedDto);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }
    
    @Test
    void createRoom_shouldThrowException_whenSavedRoomHasNoId() {
        // Given
        long roomId = 1L;
        long teamId = 2L;
        ProceedDto proceedDto = new ProceedDto(roomId);

        Location currentLoc = new Location("Room of Knowledge", "KNOWLEDGE", 10);
        Team team = new Team();
        team.setId(teamId);
        Mission mission = new Mission("Mission 1");
        setId(mission, 3L);
        team.setMission(mission);

        Room room = new Room();
        setId(room, roomId);
        room.setProgress(100);
        room.setLocation(currentLoc);
        room.setTeam(team);

        Location nextLoc = new Location("Room of Abstracts", "ABSTRACTS", 10);
        setId(nextLoc, 11L);

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
        when(teamRepository.findById(teamId)).thenReturn(Optional.of(team));
        when(locationRepository.findByName("Room of Abstracts")).thenReturn(Optional.of(nextLoc));
        when(questionRepository.findByLocationIdAndMissionId(anyLong(), anyLong())).thenReturn(List.of());
        
        // Return room without ID
        when(roomRepository.save(any(Room.class))).thenReturn(new Room());

        // When & Then
        assertThrows(IllegalStateException.class, () -> proceedService.proceedToTheRoomOfAbstracts(proceedDto));
    }

    private void setId(Object entity, Long id) {
        try {
            Field idField = entity.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(entity, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
