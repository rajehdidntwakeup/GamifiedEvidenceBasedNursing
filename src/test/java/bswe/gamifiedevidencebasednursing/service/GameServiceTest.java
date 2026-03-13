package bswe.gamifiedevidencebasednursing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import bswe.gamifiedevidencebasednursing.domain.Game;
import bswe.gamifiedevidencebasednursing.domain.Question;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.domain.enums.Mission;
import bswe.gamifiedevidencebasednursing.repository.GameRepository;
import bswe.gamifiedevidencebasednursing.repository.QuestionRepository;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import bswe.gamifiedevidencebasednursing.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"spring.datasource.url=jdbc:h2:mem:GameServiceTest;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE"})
public class GameServiceTest {

  @Autowired
  private GameService gameService;

  @Autowired
  private TeamService teamService;

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private RoomRepository roomRepository;

  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  private GameRepository gameRepository;


  @Test
  public void testGameService() {
    gameService.createGame("1234");
    Game game = gameRepository.findAll().getFirst();
    assertNotNull(game);
    assertNotNull(teamRepository.findByGameAndMission(game.getId(), Mission.WOUND_CARE_FOR_PRESSURE_ULCERS));
    List<Team> teamList = teamRepository.findAll();
    assertNotNull(teamList);
    assertEquals(5, teamList.size());

    List<Team> teamList1 = teamRepository.findTeamsByGameId(game.getId());
    assertNotNull(teamList1);
    assertEquals(5, teamList1.size());

    Room room1 = roomRepository.findRoomByTeam(teamList.getFirst().getId());
    Room room2 = roomRepository.findRoomByTeam(teamList.get(1).getId());
    Room room3 = roomRepository.findRoomByTeam(teamList.get(2).getId());
    Room room4 = roomRepository.findRoomByTeam(teamList.get(3).getId());
    Room room5 = roomRepository.findRoomByTeam(teamList.get(4).getId());

    assertNotNull(room1);
    assertNotNull(room2);
    assertNotNull(room3);
    assertNotNull(room4);
    assertNotNull(room5);

    List<Question> questions1 = questionRepository.findQuestionsByRoomId(room1.getId());
    List<Question> questions2 = questionRepository.findQuestionsByRoomId(room2.getId());
    List<Question> questions3 = questionRepository.findQuestionsByRoomId(room3.getId());
    List<Question> questions4 = questionRepository.findQuestionsByRoomId(room4.getId());
    List<Question> questions5 = questionRepository.findQuestionsByRoomId(room5.getId());
    assertNotNull(questions1);
    assertNotNull(questions2);
    assertNotNull(questions3);
    assertNotNull(questions4);
    assertNotNull(questions5);
    assertEquals(10, questions1.size());
    assertEquals(10, questions2.size());
    assertEquals(10, questions3.size());
    assertEquals(10, questions4.size());
    assertEquals(10, questions5.size());

  }
}
