package website.skillforge.be.services.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CreateAssignmentRequestDTO;
import website.skillforge.be.entities.assignment.Assignment;
import website.skillforge.be.repository.assignmentRepo.AssignmentRepository;
import website.skillforge.be.repository.LessonRepository;

import java.util.Date;
import java.util.List;

@Service
public class AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private LessonRepository lessonRepository;

    public Assignment createAssignment(CreateAssignmentRequestDTO createAssignmentRequestDTO) {
        Assignment assigment = new Assignment();
        Date date = new Date();
        assigment.setName(createAssignmentRequestDTO.getName());
        assigment.setDescription(createAssignmentRequestDTO.getDescription());
        assigment.setCreatedDate(date);
        assigment.setLastUpdatedDate(date);
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
            Date date = new Date();
            existingAssignment.setName(assignment.getName());
            existingAssignment.setDescription(assignment.getDescription());
            existingAssignment.setLastUpdatedDate(date);
            return assignmentRepository.save(existingAssignment);
        }
        return null;
    }

    public List<Assignment> getAllAssignments() {

        return assignmentRepository.findAll();
    }

}
