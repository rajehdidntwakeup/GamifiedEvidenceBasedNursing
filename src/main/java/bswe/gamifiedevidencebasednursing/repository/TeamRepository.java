package bswe.gamifiedevidencebasednursing.repository;


import bswe.gamifiedevidencebasednursing.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

  @Query(
      "SELECT t.password FROM Team t " +
          "WHERE t.game.id =:gameId " +
          "AND t.mission.id =:missionId"
  )
  String findPasswordByGameIdAndMissionId(Long gameId, Long missionId);

}
