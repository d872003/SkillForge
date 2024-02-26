package website.skillforge.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.Criteria;
@Repository
public interface CriteriaRepository extends JpaRepository<Criteria, Long> {
    Criteria findCriteriaByName(String name);

    Criteria findCriteriaById(long id);
}
