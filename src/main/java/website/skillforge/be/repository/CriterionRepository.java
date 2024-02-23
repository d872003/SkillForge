package website.skillforge.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.Criterion;

@Repository
public interface CriterionRepository extends JpaRepository<Criterion, Long> {
    Criterion findCriterionById(long id);

    Criterion findCriterionByName(String name);
}
