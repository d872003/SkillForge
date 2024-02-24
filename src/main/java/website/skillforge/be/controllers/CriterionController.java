package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.createDTO.CreateCriterionRequestDTO;
import website.skillforge.be.entities.Criterion;
import website.skillforge.be.services.CriterionService;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class CriterionController {
    @Autowired
    private CriterionService criterionService;

    @PostMapping("/criterion")
    public ResponseEntity createCriterion(@RequestBody CreateCriterionRequestDTO createCriterionRequestDTO) {
        Criterion criterion = criterionService.createCriterion(createCriterionRequestDTO);
        return ResponseEntity.ok(criterion);
    }

    @DeleteMapping("/criterion")
    public ResponseEntity deleteCriterion(long id) {
        Criterion criterion = criterionService.deleteCriterion(id);
        return ResponseEntity.ok(criterion);
    }

    @PutMapping("/criterion")
    public ResponseEntity updateCriterion(long id, @RequestBody CreateCriterionRequestDTO createCriterionRequestDTO) {
        Criterion criterion = criterionService.updateCriterion(id, createCriterionRequestDTO);
        return ResponseEntity.ok(criterion);
    }

    @GetMapping("/criterion/{id}")
    public ResponseEntity getCriterionById(@PathVariable Long id) {
        Criterion criterion = criterionService.getCriterionById(id);
        return ResponseEntity.ok(criterion);
    }

    @GetMapping("/criterion/{name}")
    public ResponseEntity getCriterionByName(@PathVariable String name) {
        Criterion criterion = criterionService.getCriterionByName(name);
        return ResponseEntity.ok(criterion);
    }

    @GetMapping("/criterion")
    public ResponseEntity getAllCriteria() {
        return ResponseEntity.ok(criterionService.getAllCriteria());
    }
}
