package bswe.gamifiedevidencebasednursing.repository;

import java.util.Optional;

import bswe.gamifiedevidencebasednursing.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

  @Query("SELECT g FROM Game g " +
      "WHERE g.status = 'CREATED' " +
      "OR g.status = 'RUNNING'")
  Optional<Game> findCreatedOrRunningGame();
}
