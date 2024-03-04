package website.skillforge.be.repository.assignmentRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.assignment.Rubric;
@Repository
public interface RubricRepository extends JpaRepository<Rubric, Long> {

    Rubric findRubricById(long id);
    Rubric findRubricByName(String name);


}
