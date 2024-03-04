package website.skillforge.be.controllers.assignmentControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.createDTO.CreateAssignmentRequestDTO;
import website.skillforge.be.entities.assignment.Assignment;
import website.skillforge.be.services.assignment.AssignmentService;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;

    @PostMapping("/assignment")
    public ResponseEntity createAssignment(@RequestBody CreateAssignmentRequestDTO createAssignmentRequestDTO) {
        Assignment assignment = assignmentService.createAssignment(createAssignmentRequestDTO);
        return ResponseEntity.ok(assignment);
    }
    @DeleteMapping("/assignment/{id}")
    public ResponseEntity deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignmentById(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/assignment/{id}")
    public ResponseEntity updateAssignment(@PathVariable Long id, @RequestBody CreateAssignmentRequestDTO createAssignmentRequestDTO) {
        Assignment assignment = assignmentService.updateAssignment(id, createAssignmentRequestDTO);
        return ResponseEntity.ok(assignment);
    }
    @GetMapping("/assignment")
    public ResponseEntity getAllAssignments() {
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }
    @GetMapping("/assignment/{id}")
    public ResponseEntity getAssignmentById(@PathVariable Long id) {
        return ResponseEntity.ok(assignmentService.findAssignmentById(id));
    }
    @GetMapping("/assignment/{name}")
    public ResponseEntity getAssignmentByName(@PathVariable String name) {
        return ResponseEntity.ok(assignmentService.findAssignmentByName(name));
    }




}
