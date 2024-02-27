package website.skillforge.be.controllers.quizControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.createDTO.CreateQuizRequestDTO;
import website.skillforge.be.entities.quiz.Quiz;
import website.skillforge.be.services.quiz.QuizService;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("/quiz")
    public ResponseEntity createQuiz(@RequestBody CreateQuizRequestDTO createQuizRequestDTO) {
        Quiz quiz = quizService.createQuiz(createQuizRequestDTO);
        return ResponseEntity.ok(quiz);
    }

    @DeleteMapping("/quiz")
    public ResponseEntity deleteQuiz(@RequestBody Long id) {
        quizService.deleteQuizById(id);
        return ResponseEntity.ok("Quiz deleted successfully");
    }

    @PutMapping("/quiz")
    public ResponseEntity updateQuiz(@PathVariable Long id, @RequestBody CreateQuizRequestDTO createQuizRequestDTO) {
        Quiz quiz = quizService.updateQuiz(id, createQuizRequestDTO);
        return ResponseEntity.ok(quiz);
    }

    @GetMapping("/quiz/{id}")
    public ResponseEntity getQuizById(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.getQuizById(id));
    }

    @GetMapping("/quiz/{name}")
    public ResponseEntity getQuizByName(@PathVariable String name) {
        return ResponseEntity.ok(quizService.getQuizByName(name));
    }

    @GetMapping("/quiz")
    public ResponseEntity getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

}
