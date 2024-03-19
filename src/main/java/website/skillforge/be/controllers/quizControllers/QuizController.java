package website.skillforge.be.controllers.quizControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.services.quiz.QuizService;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class QuizController {
    @Autowired
    QuizService quizService;

    @DeleteMapping("/quiz")
    public ResponseEntity deleteQuiz(@RequestBody Long id) {
        quizService.deleteQuizById(id);
        return ResponseEntity.ok("Quiz deleted successfully");
    }

    @GetMapping("/quiz/{id}")
    public ResponseEntity getQuizById(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.getQuizById(id));
    }

    @GetMapping("/quiz/lessonId")
    public ResponseEntity getQuizByLessonId(@RequestParam Long id) {
        return ResponseEntity.ok(quizService.getQuizByLessonId(id));
    }

    @GetMapping("/quiz")
    public ResponseEntity getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

}
