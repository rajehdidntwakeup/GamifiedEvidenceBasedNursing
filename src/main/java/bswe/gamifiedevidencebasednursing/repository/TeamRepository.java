package bswe.gamifiedevidencebasednursing.repository;


import java.util.List;

import bswe.gamifiedevidencebasednursing.domain.Team;
import bswe.gamifiedevidencebasednursing.feature.admindashboard.dto.response.MissionPasswordDto;
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



  @Query(
      "SELECT new bswe.gamifiedevidencebasednursing.feature.admindashboard.dto.response." +
          "MissionPasswordDto(t.mission.name, t.password) " +
          "FROM Team t " +
          "WHERE t.game.id =:gameId"
  )
  List<MissionPasswordDto> findAllMissionPasswordsByGameId(long gameId);

}
