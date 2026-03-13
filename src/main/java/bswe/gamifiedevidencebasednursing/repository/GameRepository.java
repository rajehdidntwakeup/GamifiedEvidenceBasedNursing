package bswe.gamifiedevidencebasednursing.repository;

import bswe.gamifiedevidencebasednursing.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
