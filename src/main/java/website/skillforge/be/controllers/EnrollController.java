package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.entities.Course;
import website.skillforge.be.entities.CourseEnrollment;
import website.skillforge.be.enums.status.EnrollStatus;
import website.skillforge.be.repository.CourseEnrollmentRepository;
import website.skillforge.be.repository.CourseRepository;
import website.skillforge.be.util.AccountUtil;

import java.util.Date;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class EnrollController {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CourseEnrollmentRepository courseEnrollmentRepository;
    @Autowired
    private AccountUtil accountUtils;

    @PostMapping("/enroll")
    public void enrollCourseAfterPay(@RequestParam Long id) {
        CourseEnrollment courseEnrollment = new CourseEnrollment();
        Course course = courseRepository.findCourseById(id);
        courseEnrollment.setCourse(course);
        courseEnrollment.setStartDate(new Date());
        courseEnrollment.setAccount(accountUtils.getCurrentAccount());
        courseEnrollment.setStatus(EnrollStatus.ACTIVE);
        courseEnrollmentRepository.save(courseEnrollment);
    }

    @GetMapping("/enroll")
    public ResponseEntity getEnrolledCourse(@RequestParam Long id) {
        Course course = courseRepository.findCourseById(id);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/enroll/all")
    public ResponseEntity getAllEnrolledCourse() {
        return ResponseEntity.ok(courseEnrollmentRepository.findAll());
    }
}
