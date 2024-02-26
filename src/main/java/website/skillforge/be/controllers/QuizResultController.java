package website.skillforge.be.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import website.skillforge.be.dto.createDTO.CreateQuizResultRequestDto;
import website.skillforge.be.entities.QuizResult;
import website.skillforge.be.services.QuizResultService;

@RestController
@CrossOrigin("*")
public class QuizResultController {
    @Autowired
    private QuizResultService quizResultService;

    @PostMapping("/quiz-result")
    public ResponseEntity createQuizResult(CreateQuizResultRequestDto createQuizResultRequest) {
        QuizResult quizResult = quizResultService.createQuizResult(createQuizResultRequest);
        return ResponseEntity.ok(quizResult);
    }

    @GetMapping("/quiz-result")
    public ResponseEntity getQuizResult(Long id) {
        QuizResult quizResult = quizResultService.getQuizResult(id);
        return ResponseEntity.ok(quizResult);
    }

}
