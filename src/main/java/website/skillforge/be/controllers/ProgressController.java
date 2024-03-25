package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.createDTO.CreateProgressDto;
import website.skillforge.be.entities.Progress;
import website.skillforge.be.repository.ProgressRepository;
import website.skillforge.be.services.ProgressService;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class ProgressController {
    @Autowired
    private ProgressRepository progressRepository;
    @Autowired
    private ProgressService progressService;

    @PostMapping("/progress")
    public ResponseEntity<?> createProgress(@RequestBody CreateProgressDto createProgressDto) {
        Progress progress = progressService.createProgress(createProgressDto);
        return ResponseEntity.ok(progress);
    }

    @GetMapping("/progress/percent/courseId")
    public ResponseEntity<?> percentCourse(@RequestParam long courseId) {
        double percent = progressService.percentCourse(courseId);
        return ResponseEntity.ok(percent);
    }

}
