package website.skillforge.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.courses.CourseEnrollment;

import java.util.List;

@Repository
public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, Long> {
    CourseEnrollment findCourseEnrollmentById(long id);

    List<CourseEnrollment> findCourseEnrollmentByAccount_id(long account_id);

    List<CourseEnrollment> findCourseEnrollmentByCourse_id(long course_id);
}
