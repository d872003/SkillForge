package website.skillforge.be.services.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CreateRubricRequestDTO;
import website.skillforge.be.entities.assignment.Rubric;
import website.skillforge.be.repository.assignmentRepo.AssignmentRepository;
import website.skillforge.be.repository.assignmentRepo.RubricRepository;

import java.util.Date;
import java.util.List;

@Service
public class RubricService {
    @Autowired
    private RubricRepository rubricRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;

    public Rubric createRubric(CreateRubricRequestDTO createRubricRequest) {
        Rubric rubric = new Rubric();
        Date date = new Date();
        rubric.setName(createRubricRequest.getName());
        rubric.setDescription(createRubricRequest.getDescription());
        rubric.setCreateDate(date);
        rubric.setLastUpdatedDate(date);
        rubric.setAssignment(assignmentRepository.findById(createRubricRequest.getAssignment_id()).get());
        return rubricRepository.save(rubric);
    }

    public Rubric deleteRubricById(Long rubricId) {
        Rubric rubric = rubricRepository.findById(rubricId).get();
        rubricRepository.delete(rubric);
        return rubric;
    }

    public Rubric updateRubric(Long id, CreateRubricRequestDTO createRubricRequest) {
        Rubric rubric = rubricRepository.findById(id).get();
        if (rubric != null) {
            return null;
        }
        Date date = new Date();
        rubric.setName(createRubricRequest.getName());
        rubric.setDescription(createRubricRequest.getDescription());
        rubric.setLastUpdatedDate(date);
        rubric.setAssignment(assignmentRepository.findById(createRubricRequest.getAssignment_id()).get());
        return rubricRepository.save(rubric);
    }

    public Rubric getRubricById(Long id) {
        return rubricRepository.findById(id).get();
    }

    public Rubric getRubricByName(String name) {
        return rubricRepository.findRubricByName(name);
    }

    public List<Rubric> getAllRubrics() {
        return rubricRepository.findAll();
    }
}
