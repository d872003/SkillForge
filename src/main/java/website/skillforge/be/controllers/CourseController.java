package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.CourseDetailResponse;
import website.skillforge.be.dto.createDTO.CreateCourseRequestDTO;
import website.skillforge.be.entities.Chapter;
import website.skillforge.be.entities.Course;
import website.skillforge.be.entities.Lesson;
import website.skillforge.be.services.ChapterService;
import website.skillforge.be.services.CourseService;
import website.skillforge.be.services.LessonService;
import website.skillforge.be.services.quiz.QuizService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    ChapterService chapterService;
    @Autowired
    LessonService lessonService;
    @Autowired
    QuizService quizService;
    @PostMapping("/course")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('TEACHER')")
    public ResponseEntity createCourse(@RequestBody CreateCourseRequestDTO createCourseRequestDTO) {
        Course course = courseService.createCourse(createCourseRequestDTO);
        return ResponseEntity.ok(course);
    }
    @DeleteMapping("/course/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('TEACHER')")
    public ResponseEntity deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @PutMapping("/course/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('TEACHER')")
    public ResponseEntity updateCourse(@PathVariable Long id, @RequestBody CreateCourseRequestDTO updateCourseDTO) {
        Course newCourse = courseService.updateCourse(id, updateCourseDTO);
        return ResponseEntity.ok(newCourse);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/courseByName/{name}")
    public ResponseEntity getCourseByName(@PathVariable String name) {
        Course course = courseService.getCourseByName(name);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/course/detail/{id}")
    public ResponseEntity getCourseDetail(@PathVariable Long id) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("chapters", chapterService.GetChaptersByCourseId(id));
        responseData.put("course", courseService.getCourseDetail(id));
        List<Chapter> chapters = (List<Chapter>) responseData.get("chapters");
        if (chapters != null && !chapters.isEmpty()) {
            Long chapterId = chapters.get(0).getId();
            responseData.put("lessons", lessonService.getAllLessonByChapterId(chapterId));
            List<Lesson> lesson = (List<Lesson>) responseData.get("lessons");
            if (lesson != null && !lesson.isEmpty()) {
                responseData.put("quizzes", quizService.getQuizByLessonId(lesson.get(0).getId()));
            }
        }
        return ResponseEntity.ok(responseData);
    }


    @GetMapping("/course")
    public ResponseEntity getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }
}

