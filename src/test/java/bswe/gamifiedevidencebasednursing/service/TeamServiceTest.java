package bswe.gamifiedevidencebasednursing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import bswe.gamifiedevidencebasednursing.domain.Question;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.domain.enums.Mission;
import bswe.gamifiedevidencebasednursing.repository.QuestionRepository;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import bswe.gamifiedevidencebasednursing.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"spring.datasource.url=jdbc:h2:mem:TeamServiceTest;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE"})
public class TeamServiceTest {

  @Autowired
  private TeamService teamService;

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private RoomRepository roomRepository;

  @Autowired
  private QuestionRepository questionRepository;


  @Test
  public void testCreateTeam() {
    teamService.createTeam(null, Mission.FALL_PREVENTION_IN_GERIATRICS);

    Team team = teamRepository.findTeamByMission(Mission.FALL_PREVENTION_IN_GERIATRICS);
    assertNotNull(team);

    Room room = roomRepository.findRoomByTeamAndMission(team.getId(), Mission.FALL_PREVENTION_IN_GERIATRICS);
    assertNotNull(room);

    List<Question> questions = questionRepository.findQuestionsByRoomId(room.getId());
    assertNotNull(questions);
    assertEquals(10, questions.size());
  }
}
