package bswe.gamifiedevidencebasednursing.repository;



import bswe.gamifiedevidencebasednursing.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

  @Query("SELECT r FROM Room r " +
      "JOIN r.team t " +
      "JOIN t.mission m " +
      "JOIN r.location l " +
      "WHERE t.game.id = :gameId " +
      "AND m.id = :missionId " +
      "AND l.name = :locationName")
  Room findRoomByGameIdAndMissionIdAndLocationName(Long gameId, Long missionId, String locationName);

}
