package bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.service;

import bswe.gamifiedevidencebasednursing.domain.*;
import bswe.gamifiedevidencebasednursing.domain.enums.GameStatus;
import bswe.gamifiedevidencebasednursing.domain.enums.Status;
import bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.dto.request.ProceedDto;
import bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.dto.response.RoomResponseDto;
import bswe.gamifiedevidencebasednursing.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProceedServiceIntegrationTest {

    @Autowired
    private ProceedService proceedService;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private DocumentRepository documentRepository;

    private Location roomOfKnowledge;
    private Location roomOfAbstracts;
    private Mission mission;
    private Team team;
    private Game game;

    @BeforeEach
    void setUp() {
        roomRepository.deleteAll();
        answerRepository.deleteAll();
        documentRepository.deleteAll();
        questionRepository.deleteAll();
        teamRepository.deleteAll();
        gameRepository.deleteAll();
        locationRepository.deleteAll();
        missionRepository.deleteAll();

        roomOfKnowledge = new Location("Room of Knowledge", "KNOWLEDGE", 10);
        locationRepository.save(roomOfKnowledge);

        roomOfAbstracts = new Location("Room of Abstracts", "ABSTRACTS", 10);
        locationRepository.save(roomOfAbstracts);

        mission = new Mission("Mission 1");
        missionRepository.save(mission);

        game = new Game(GameStatus.CREATED);
        gameRepository.save(game);

        team = new Team(Status.STARTED, mission, false);
        team.setGame(game);
        team.setPassword("teamPassword");
        teamRepository.save(team);

        Question q1 = new Question("Abstract Question", roomOfAbstracts);
        q1.setId(100L);
        q1.setMissions(Set.of(mission));
        questionRepository.save(q1);
    }

    @Test
    void proceedToTheRoomOfAbstracts_shouldSucceed() {
        // Given
        Room currentRoom = new Room();
        currentRoom.setLocation(roomOfKnowledge);
        currentRoom.setTeam(team);
        currentRoom.setProgress(100);
        currentRoom = roomRepository.save(currentRoom);

        ProceedDto proceedDto = new ProceedDto(currentRoom.getId());

        // When
        ResponseEntity<RoomResponseDto> response = proceedService.proceedToTheRoomOfAbstracts(proceedDto);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getRoomId() > 0);
        assertEquals(mission.getId(), response.getBody().getMissionId());
        
        // Verify room was actually created in DB
        assertTrue(roomRepository.findById(response.getBody().getRoomId()).isPresent());
        Room nextRoom = roomRepository.findById(response.getBody().getRoomId()).get();
        assertEquals("Room of Abstracts", nextRoom.getLocation().getName());
        assertEquals(team.getId(), nextRoom.getTeam().getId());
    }

    @Test
    void proceedToTheRoomOfAbstracts_shouldReturnNotFound_whenProgressIsLow() {
        // Given
        Room currentRoom = new Room();
        currentRoom.setLocation(roomOfKnowledge);
        currentRoom.setTeam(team);
        currentRoom.setProgress(50);
        currentRoom = roomRepository.save(currentRoom);

        ProceedDto proceedDto = new ProceedDto(currentRoom.getId());

        // When
        ResponseEntity<RoomResponseDto> response = proceedService.proceedToTheRoomOfAbstracts(proceedDto);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
