package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.createDTO.CreateQuestionRequestDTO;
import website.skillforge.be.entities.Question;
import website.skillforge.be.services.QuestionService;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping("/question")
    public ResponseEntity createQuestion(@RequestBody CreateQuestionRequestDTO createQuestionRequestDTO) {
        Question question = questionService.createQuestion(createQuestionRequestDTO);
        return ResponseEntity.ok(question);
    }

    @DeleteMapping("/question/{id}")
    public ResponseEntity deleteQuestion(@RequestBody Long id) {
        questionService.deleteQuestionById(id);
        return ResponseEntity.ok("Question deleted successfully");
    }

    @PutMapping("/question/{id}")
    public ResponseEntity updateQuestion(@PathVariable Long id, @RequestBody CreateQuestionRequestDTO createQuestionRequestDTO) {
        Question question = questionService.updateQuestion(id, createQuestionRequestDTO);
        return ResponseEntity.ok(question);
    }

    @GetMapping("/question/{id}")
    public ResponseEntity getQuestionById(@PathVariable Long id) {
        Question question = questionService.getQuestionById(id);
        return ResponseEntity.ok(question);
    }

    @GetMapping("/questions")
    public ResponseEntity getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }
}
