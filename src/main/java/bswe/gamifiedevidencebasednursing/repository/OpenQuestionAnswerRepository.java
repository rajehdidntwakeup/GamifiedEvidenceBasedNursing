package bswe.gamifiedevidencebasednursing.repository;

import java.util.List;

import bswe.gamifiedevidencebasednursing.domain.OpenQuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenQuestionAnswerRepository extends JpaRepository<OpenQuestionAnswer, Long> {

  @Query("SELECT oqa FROM OpenQuestionAnswer oqa " +
      "WHERE oqa.room.id = :roomId " +
      "AND oqa.question.id = :questionId")
  OpenQuestionAnswer findByRoomIdAndQuestionId(Long roomId, Long questionId);

  @Query("SELECT oqa FROM OpenQuestionAnswer oqa WHERE oqa.room.id = :roomId")
  List<OpenQuestionAnswer> findAllByRoomId(Long roomId);
}
