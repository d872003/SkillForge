package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.CreateFeedbackDto;
import website.skillforge.be.services.FeedbackService;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @PostMapping("/feedback")
    public ResponseEntity<?> createFeedback(@RequestBody CreateFeedbackDto feedbackDto) {
        return ResponseEntity.ok(feedbackService.createFeedback(feedbackDto));
    }

    @GetMapping("/feedback/{id}")
    public ResponseEntity<?> getFeedbackById(@PathVariable Long id) {
        return ResponseEntity.ok(feedbackService.getFeedbackById(id));
    }

    @GetMapping("/feedback/courseId")
    public ResponseEntity<?> getFeedbackByCourseId(@RequestParam Long id) {
        return ResponseEntity.ok(feedbackService.getFeedbackByCourseId(id));
    }

    @GetMapping("/feedback/accountId")
    public ResponseEntity<?> getFeedbackByAccountId(@RequestParam Long id) {
        return ResponseEntity.ok(feedbackService.getFeedbackByAccountId(id));
    }
}
