package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CreateAssignmentRequestDTO;
import website.skillforge.be.entities.Assignment;
import website.skillforge.be.repository.AssignmentRepository;
import website.skillforge.be.repository.LessonRepository;

import java.util.List;

@Service
public class AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private LessonRepository lessonRepository;

    public Assignment createAssignment(CreateAssignmentRequestDTO createAssignmentRequestDTO) {
        Assignment assigment = new Assignment();
        assigment.setName(createAssignmentRequestDTO.getName());
        assigment.setDescription(createAssignmentRequestDTO.getDescription());
//        assigment.setCreatedDate(createAssignmentRequestDTO.getCreatedDate());
        assigment.setLesson(lessonRepository.findLessonById(createAssignmentRequestDTO.getLesson_id()));
        return assignmentRepository.save(assigment);
    }
    public Assignment findAssignmentById(Long id) {
        return assignmentRepository.findAssignmentById(id);
    }
    public Assignment findAssignmentByName(String name) {
        return assignmentRepository.findAssignmentByName(name);
    }
    public int deleteAssignmentById(Long id) {
        assignmentRepository.deleteById(id);
        return 1;
    }
    public Assignment updateAssignment(Long id, CreateAssignmentRequestDTO assignment) {
        Assignment existingAssignment = assignmentRepository.findAssignmentById(id);
        if (existingAssignment != null) {
            existingAssignment.setName(assignment.getName());
            existingAssignment.setDescription(assignment.getDescription());
            return assignmentRepository.save(existingAssignment);
        }
        return null;
    }

    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

}
