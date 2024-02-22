package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.createDTO.CreateAnswerRequestDTO;
import website.skillforge.be.entities.Answer;
import website.skillforge.be.services.AnswerService;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @PostMapping("/answer")
    public ResponseEntity createAnswer(@RequestBody CreateAnswerRequestDTO createAnswerRequestDTO) {
        Answer answer = answerService.createAnswer(createAnswerRequestDTO);
        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("/answer/{id}")
    public ResponseEntity deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswerById(id);
        return ResponseEntity.ok("deleted");
    }

    @PutMapping("/answer/{id}")
    public ResponseEntity updateAnswer(@PathVariable Long id, @RequestBody CreateAnswerRequestDTO createAnswerRequestDTO) {
        Answer answer = answerService.updateAnswer(id, createAnswerRequestDTO);
        return ResponseEntity.ok(answer);
    }

}
