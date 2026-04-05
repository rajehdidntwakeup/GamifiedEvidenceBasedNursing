package bswe.gamifiedevidencebasednursing.repository;

import bswe.gamifiedevidencebasednursing.domain.Mission;
import bswe.gamifiedevidencebasednursing.feature.landingpage.dto.response.MissionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

  @Query("SELECT m.name FROM Mission m")
  List<String> findAllMissionNames();

  @Query("" +
      "SELECT " +
      "new bswe.gamifiedevidencebasednursing.feature.landingpage.dto.response.MissionDto(m.id, m.name) " +
      "FROM Mission m")
  List<MissionDto> findAllMissionDtos();
  Optional<Mission> findByName(String name);
}
