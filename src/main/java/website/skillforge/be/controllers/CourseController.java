package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.createDTO.CreateCourseRequestDTO;
import website.skillforge.be.entities.Account;
import website.skillforge.be.entities.Chapter;
import website.skillforge.be.entities.Course;
import website.skillforge.be.entities.Lesson;
import website.skillforge.be.enums.Role;
import website.skillforge.be.repository.CourseRepository;
import website.skillforge.be.services.ChapterService;
import website.skillforge.be.services.CourseService;
import website.skillforge.be.services.LessonService;
import website.skillforge.be.services.quiz.QuizService;
import website.skillforge.be.util.AccountUtil;

import javax.annotation.security.PermitAll;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private QuizService quizService;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private AccountUtil accountUtil;
    @PostMapping("/course")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('TEACHER')")
    public ResponseEntity<?> createCourse(@RequestBody CreateCourseRequestDTO createCourseRequestDTO) {
        Course course = courseService.createCourse(createCourseRequestDTO);
        return ResponseEntity.ok(course);
    }
    @DeleteMapping("/course/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('TEACHER')")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @PutMapping("/course/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('TEACHER')")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody CreateCourseRequestDTO updateCourseDTO) {
        Course newCourse = courseService.updateCourse(id, updateCourseDTO);
        return ResponseEntity.ok(newCourse);
    }

    @GetMapping("/course/{id}")
    @PermitAll
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/courseByName/{name}")
    public ResponseEntity<?> getCourseByName(@PathVariable String name) {
        Course course = courseService.getCourseByName(name);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/courseByCode/{code}")
    public ResponseEntity<?> getCourseByCode(@PathVariable String code) {
        Course course = courseService.getCourseByCode(code);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/getCourseById/{id}")
    public ResponseEntity<?> getCourseDetail(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseDetail(id));
    }

    @GetMapping("/getCourseByTeacherId")
    public ResponseEntity<?> getCourseByTeacherId() {
        return ResponseEntity.ok(courseService.getCourseByTeacherId());
    }

    @GetMapping("/courseDetailAll")
    public ResponseEntity<?> getCourseDetailAll() {
        return ResponseEntity.ok(courseService.getCourseDetail());
    }

    @GetMapping("/course")
    public ResponseEntity<?> getAllCourses() {

        return ResponseEntity.ok(courseService.getAllCourses());
    }

}

