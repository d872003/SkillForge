package website.skillforge.be.controllers.quizControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.createDTO.quizDto.GetQuizAnswerRequestDto;
import website.skillforge.be.entities.quiz.QuizResult;
import website.skillforge.be.services.quiz.QuizResultService;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class QuizResultController {
    @Autowired
    private QuizResultService quizResultService;

    @PostMapping("/quizResult")
    public ResponseEntity createQuizResult(@RequestBody GetQuizAnswerRequestDto dto) {
        QuizResult quizResult = quizResultService.createQuizResult(dto);
        return ResponseEntity.ok(quizResult);
    }

    @GetMapping("/quizResult/{id}")
    public ResponseEntity getQuizResult(@PathVariable Long id) {
        return ResponseEntity.ok(quizResultService.getQuizResult(id));
    }

}
