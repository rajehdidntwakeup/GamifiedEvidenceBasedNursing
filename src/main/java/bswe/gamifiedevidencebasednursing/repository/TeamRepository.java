package bswe.gamifiedevidencebasednursing.repository;

import java.util.List;

import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.domain.enums.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

  Team findTeamByMission(Mission mission);

  @Query("SELECT t FROM Team t WHERE t.game.id = :gameId")
  List<Team> findTeamsByGameId(Long gameId);

  @Query("SELECT t FROM Team t " +
      "WHERE t.game.id = :gameId " +
      "AND t.mission = :mission")
  Team findByGameAndMission(Long gameId, Mission mission);
}
