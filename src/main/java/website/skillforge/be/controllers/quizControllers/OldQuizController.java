//package website.skillforge.be.controllers.quizControllers;
//
//
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import website.skillforge.be.services.quiz.OldQuizService;
//
//@RestController
//@CrossOrigin("*")
//@SecurityRequirement(name = "api")
//public class OldQuizController {
//    @Autowired
//    OldQuizService oldQuizService;
//
//    @PostMapping("/oldquiz")
//    public ResponseEntity createQuiz(@RequestParam Long id) {
//
//        return ResponseEntity.ok(oldQuizService.saveOldQuizQuestions(id));
//
//    }
//
//    @PostMapping("/oldquizanswer")
//    public ResponseEntity createQuizAnswer(@RequestParam Long id) {
//        return ResponseEntity.ok(oldQuizService.saveOldQuizAnswers(id));
//    }
//}
