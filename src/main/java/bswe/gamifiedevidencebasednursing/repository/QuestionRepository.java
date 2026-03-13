package bswe.gamifiedevidencebasednursing.repository;

import java.util.List;

import bswe.gamifiedevidencebasednursing.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

  @Query("SELECT q FROM Question q " +
      "JOIN q.rooms m " +
      "WHERE m.id = :roomId")
  List<Question> findQuestionsByRoomId(Long roomId);
}
