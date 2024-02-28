package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.createDTO.CreateQuizRequestDTO;
import website.skillforge.be.dto.createDTO.CreateRubricRequestDTO;
import website.skillforge.be.dto.updateDTO.UpdateRubricDTO;
import website.skillforge.be.entities.Rubric;
import website.skillforge.be.services.RubricService;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class RubricController {
    @Autowired
    RubricService rubricService;

    @PostMapping("/Rubric")
    public ResponseEntity createRubric(@RequestBody CreateRubricRequestDTO createRubricRequestDTO){
        Rubric rubric = rubricService.createRubric(createRubricRequestDTO);
        return ResponseEntity.ok(rubric);
    }
    @PutMapping("/Rubric/update")
    public ResponseEntity updateRubric(@PathVariable Long id, @RequestBody UpdateRubricDTO updateRubricDTO){
        Rubric newRubric = rubricService.updateRubric(id, updateRubricDTO);
        return ResponseEntity.ok(newRubric);
    }

    @DeleteMapping("/Rubric")
    public ResponseEntity deleteRubric(@RequestBody Long id){
        rubricService.deleteRubric(id);
        return ResponseEntity.ok("Rubric delete successfully");
    }
    @GetMapping("/Rubric/All")
    public ResponseEntity getAllRubric(){
        return ResponseEntity.ok(rubricService.getAllRubric());
    }
    @GetMapping("/Rubric/{id}")
    public ResponseEntity getRubricById(@RequestBody Long id){
        Rubric rubric = rubricService.getRubricById(id);
        return ResponseEntity.ok(rubric);
    }
    @GetMapping("/Rubric/{name}")
    public ResponseEntity getRubricByName(@PathVariable String name){
        Rubric rubric = rubricService.getRubricByName(name);
        return ResponseEntity.ok(rubric);
    }

}
