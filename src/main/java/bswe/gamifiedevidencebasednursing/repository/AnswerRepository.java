package bswe.gamifiedevidencebasednursing.repository;

import bswe.gamifiedevidencebasednursing.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

  @Query("SELECT a FROM Answer a " +
      "WHERE a.question.id = :questionId " +
      "AND a.isCorrect = true")
  Answer findCorrectAnswerByQuestionId(Long questionId);
}
