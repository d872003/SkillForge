package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.createDTO.EnrolledCourseDetailResponse;
import website.skillforge.be.entities.courses.Course;
import website.skillforge.be.entities.courses.CourseEnrollment;
import website.skillforge.be.enums.status.EnrollStatus;
import website.skillforge.be.repository.CourseEnrollmentRepository;
import website.skillforge.be.repository.CourseRepository;
import website.skillforge.be.services.CourseService;
import website.skillforge.be.util.AccountUtil;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class EnrollController {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CourseEnrollmentRepository courseEnrollmentRepository;
    @Autowired
    CourseService courseService;
    @Autowired
    AccountUtil accountUtils;


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
    public ResponseEntity<?> getEnrolledCourse() {
        List<EnrolledCourseDetailResponse> course = courseService.getEnrollCourseDetail();
        return ResponseEntity.ok(course);
    }

    @GetMapping("/studentEnroll/{id}")
    public ResponseEntity<?> getStudentEnrolledCourse(@PathVariable Long id) {
        List<CourseEnrollment> course = courseService.getStudentEnrollCourseDetail(id);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/enroll/{id}")
    public ResponseEntity<?> getMyCourseDetail(@PathVariable Long id) {
        EnrolledCourseDetailResponse course = courseService.getEnrollCourseDetail(id);
        return ResponseEntity.ok(course);
    }
}
