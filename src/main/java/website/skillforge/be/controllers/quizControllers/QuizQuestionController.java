package website.skillforge.be.controllers.quizControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.createDTO.CreateQuestionRequestDTO;
import website.skillforge.be.entities.quiz.QuizQuestion;
import website.skillforge.be.services.quiz.QuizQuestionService;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class QuizQuestionController {
    @Autowired
    private QuizQuestionService quizQuestionService;

    @PostMapping("/question")
    public ResponseEntity createQuestion(@RequestBody CreateQuestionRequestDTO createQuestionRequestDTO) {
        QuizQuestion question = quizQuestionService.createQuestion(createQuestionRequestDTO);
        return ResponseEntity.ok(question);
    }

    @DeleteMapping("/question/{id}")
    public ResponseEntity deleteQuestion(@RequestBody Long id) {
        quizQuestionService.deleteQuestionById(id);
        return ResponseEntity.ok("QuizQuestion deleted successfully");
    }

    @PutMapping("/question/{id}")
    public ResponseEntity updateQuestion(@PathVariable Long id, @RequestBody CreateQuestionRequestDTO createQuestionRequestDTO) {
        QuizQuestion question = quizQuestionService.updateQuestion(id, createQuestionRequestDTO);
        return ResponseEntity.ok(question);
    }

    @GetMapping("/question/{id}")
    public ResponseEntity getQuestionById(@PathVariable Long id) {
        QuizQuestion question = quizQuestionService.getQuestionById(id);
        return ResponseEntity.ok(question);
    }

    @GetMapping("/questions")
    public ResponseEntity getAllQuestions() {
        return ResponseEntity.ok(quizQuestionService.getAllQuestions());
    }
}
