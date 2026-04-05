package bswe.gamifiedevidencebasednursing.repository;

import java.util.List;

import bswe.gamifiedevidencebasednursing.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

  @Query("SELECT q FROM Question q " +
      "JOIN q.location l " +
      "WHERE l.name = 'Room of Knowledge'")
  List<Question> findQuestionsForRoomOfKnowledge();


  @Query("SELECT q FROM Question q " +
      "JOIN q.missions m " +
      "WHERE q.location.id = :locationId " +
      "AND m.id = :missionId")
  List<Question> findByLocationIdAndMissionId(long locationId, long missionId);

}
