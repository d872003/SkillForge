package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.CreateLessonRequestDTO;
import website.skillforge.be.entities.Lesson;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class LessonController {
    @Autowired
    LessonController lessonController;

    @PostMapping("/lesson")
    public ResponseEntity createLesson(@RequestBody CreateLessonRequestDTO createLessonRequestDTO){
        ResponseEntity lesson = lessonController.createLesson(createLessonRequestDTO);
        return ResponseEntity.ok(lesson);
    }

    @DeleteMapping("/lesson/{id}")
    public ResponseEntity deleteLesson(@PathVariable Long id){
        ResponseEntity lesson = lessonController.deleteLesson(id);
        return ResponseEntity.ok(lesson);
    }
    @PutMapping("/lesson/{id}")
    public ResponseEntity updateLesson(@PathVariable Long id, @RequestBody CreateLessonRequestDTO createLessonRequestDTO){
        ResponseEntity lesson = lessonController.updateLesson(id, createLessonRequestDTO);
        return ResponseEntity.ok(lesson);
    }

    @GetMapping("/lesson")
    public ResponseEntity getAllLessons() {
        return ResponseEntity.ok(lessonController.getAllLessons());
    }
    @GetMapping("/lesson/{id}")
    public ResponseEntity getLessonById(@PathVariable Long id) {
        return ResponseEntity.ok(lessonController.getLessonById(id));
    }
    @GetMapping("/lesson/{name}")
    public ResponseEntity getLessonByName(@PathVariable String name) {
        return ResponseEntity.ok(lessonController.getLessonByName(name));
    }
    @GetMapping("/lesson/course/{id}")
    public ResponseEntity getLessonsByCourseId(@PathVariable Long id) {
        return ResponseEntity.ok(lessonController.getLessonsByCourseId(id));
    }
    @GetMapping("/lesson/category/{id}")
    public ResponseEntity getLessonsByCategoryId(@PathVariable Long id) {
        return ResponseEntity.ok(lessonController.getLessonsByCategoryId(id));
    }
    @GetMapping("/lesson/user/{id}")
    public ResponseEntity getLessonsByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(lessonController.getLessonsByUserId(id));
    }


}
