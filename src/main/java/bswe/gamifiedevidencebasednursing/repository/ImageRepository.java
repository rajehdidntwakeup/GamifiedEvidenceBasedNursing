package bswe.gamifiedevidencebasednursing.repository;

import bswe.gamifiedevidencebasednursing.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Document, Long> {
}
