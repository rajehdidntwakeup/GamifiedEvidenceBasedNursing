package bswe.gamifiedevidencebasednursing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import bswe.gamifiedevidencebasednursing.domain.Question;
import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.domain.enums.Location;
import bswe.gamifiedevidencebasednursing.repository.QuestionRepository;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"spring.datasource.url=jdbc:h2:mem:RoomServiceTest;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE"})
public class RoomServiceTest {

  @Autowired
  private RoomService roomService;

  @Autowired
  private RoomRepository roomRepository;

  @Autowired
  private QuestionRepository questionRepository;

  @Test
  public void createRoomOfKnowledgeTest() {
    roomService.createRoomOfKnowledge(null);

    Room room = roomRepository.findByLocation(Location.ROOM_OF_KNOWLEDGE);
    assertNotNull(room);

    List<Question> questions = questionRepository.findQuestionsByRoomId(room.getId());
    assertNotNull(questions);
    assertEquals(10, questions.size());


  }

}
