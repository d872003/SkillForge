package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.createDTO.CreateRubricRequestDTO;
import website.skillforge.be.entities.Rubric;
import website.skillforge.be.services.RubricService;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class RubricController {
    @Autowired
    RubricService rubricService;

    @PostMapping("/rubric")
    public ResponseEntity createRubric(@RequestBody CreateRubricRequestDTO createRubricRequestDTO) {
        Rubric rubric = rubricService.createRubric(createRubricRequestDTO);
        return ResponseEntity.ok(rubric);
    }

    @DeleteMapping("/rubric")
    public ResponseEntity deleteRubric(@RequestBody Long id) {
        return ResponseEntity.ok(rubricService.deleteRubricById(id));
    }

    @PutMapping("/rubric")
    public ResponseEntity updateRubric(@PathVariable Long id, @RequestBody CreateRubricRequestDTO createRubricRequestDTO) {
        Rubric rubric = rubricService.updateRubric(id, createRubricRequestDTO);
        return ResponseEntity.ok(rubric);
    }

    @GetMapping("/rubric/{id}")
    public ResponseEntity getRubricById(@PathVariable Long id) {
        return ResponseEntity.ok(rubricService.getRubricById(id));
    }

    @GetMapping("/rubric/{name}")
    public ResponseEntity getRubricByName(@PathVariable String name) {
        return ResponseEntity.ok(rubricService.getRubricByName(name));
    }

    @GetMapping("/rubric")
    public ResponseEntity getAllRubrics() {
        return ResponseEntity.ok(rubricService.getAllRubrics());
    }
}
