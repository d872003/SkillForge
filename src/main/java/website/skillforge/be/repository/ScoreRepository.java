package website.skillforge.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import website.skillforge.be.entities.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    Score findScoreById(long id);

}
