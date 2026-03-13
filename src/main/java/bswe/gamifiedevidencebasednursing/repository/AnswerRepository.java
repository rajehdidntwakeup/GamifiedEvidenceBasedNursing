package bswe.gamifiedevidencebasednursing.repository;

import bswe.gamifiedevidencebasednursing.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
