package website.skillforge.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.Course;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByCourseName(String courseName);
    Course findByCourseId(Long courseId);
}
