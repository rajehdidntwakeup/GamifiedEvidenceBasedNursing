package bswe.gamifiedevidencebasednursing.repository;

import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.domain.enums.Location;
import bswe.gamifiedevidencebasednursing.domain.enums.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

  Room findByLocation(Location location);

  @Query("SELECT r FROM Room r " +
      "JOIN r.team t " +
      "WHERE t.id = :teamId")
  Room findRoomByTeam(Long teamId);

  @Query("SELECT r FROM Room r " +
      "JOIN r.team t " +
      "WHERE t.id = :teamId " +
      "AND t.mission = :mission")
  Room findRoomByTeamAndMission(Long teamId, Mission mission);

  @Query("SELECT r FROM Room r " +
      "JOIN r.team t " +
      "JOIN FETCH r.questions q " +
      "JOIN FETCH q.answers a " +
      "WHERE t.game.id = :gameId " +
      "AND t.mission =:mission ")
  Room findRoomByGameIdAndMission(Long gameId, Mission mission);

}
