package website.skillforge.be.repository.assignmentRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.assignment.Assignment;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    Assignment findAssignmentById(long id);

    Assignment findAssignmentByName(String name);

}
