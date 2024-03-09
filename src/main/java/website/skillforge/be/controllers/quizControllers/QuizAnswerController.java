package website.skillforge.be.controllers.quizControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.createDTO.quizDto.CreateAnswerRequestDTO;
import website.skillforge.be.entities.quiz.QuizAnswer;
import website.skillforge.be.services.quiz.QuizAnswerService;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class QuizAnswerController {
    @Autowired
    private QuizAnswerService quizAnswerService;

    @PostMapping("/answer")
    public ResponseEntity createAnswer(@RequestBody CreateAnswerRequestDTO createAnswerRequestDTO) {
        QuizAnswer answer = quizAnswerService.createAnswer(createAnswerRequestDTO);
        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("/answer/{id}")
    public ResponseEntity deleteAnswer(@PathVariable Long id) {
        quizAnswerService.deleteAnswerById(id);
        return ResponseEntity.ok("deleted");
    }

    @PutMapping("/answer/{id}")
    public ResponseEntity updateAnswer(@PathVariable Long id, @RequestBody CreateAnswerRequestDTO createAnswerRequestDTO) {
        QuizAnswer answer = quizAnswerService.updateAnswer(id, createAnswerRequestDTO);
        return ResponseEntity.ok(answer);
    }

}
