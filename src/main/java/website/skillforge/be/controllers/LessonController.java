package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.createDTO.CreateLessonRequestDTO;
import website.skillforge.be.entities.courses.Lesson;
import website.skillforge.be.services.LessonService;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class LessonController {
    @Autowired
    LessonService lessonService;


    @PostMapping("/lesson")
    public ResponseEntity<?> createLesson(@RequestBody CreateLessonRequestDTO createLessonRequestDTO) {
        Lesson lesson = lessonService.createLesson(createLessonRequestDTO);
        return ResponseEntity.ok(lesson);
    }

    @PostMapping("quiza")
    public ResponseEntity<?> createQuiz(@RequestParam String url, @RequestParam Long lessonId) {
        return ResponseEntity.ok(lessonService.createQuiz(url));
    }

    @DeleteMapping("/lesson/{id}")
    public ResponseEntity<?> deleteLesson(@PathVariable Long id) {
        lessonService.deleteLessonById(id);
        return ResponseEntity.ok("Lesson deleted successfully");
    }

    @PutMapping("/lesson/{id}")
    public ResponseEntity<?> updateLesson(@PathVariable Long id, @RequestBody CreateLessonRequestDTO updateLessonDTO) {
        Lesson lesson = lessonService.updateLesson(id, updateLessonDTO);
        return ResponseEntity.ok(lesson);
    }

    @PutMapping("/lesson/chapterId")
    public ResponseEntity<?> updateLessonByChapterId(@RequestParam Long id, @RequestBody CreateLessonRequestDTO updateLessonDTO) {
        return ResponseEntity.ok(lessonService.UpdateLessonByChapterId(id, updateLessonDTO));
    }

    @GetMapping("/lesson")
    public ResponseEntity<?> getAllLessons() {
        return ResponseEntity.ok(lessonService.getAllLesson());
    }

    @GetMapping("/lesson/{id}")
    public ResponseEntity<?> getLessonById(@PathVariable Long id) {
        return ResponseEntity.ok(lessonService.findLessonById(id));
    }
    //

    @GetMapping("/lessons/chapterId")
    public ResponseEntity getLessonsByCourseId(@RequestParam Long id) {
        return ResponseEntity.ok(lessonService.getAllLessonByChapterId(id));
    }

}
