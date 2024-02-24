package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CreateRubricRequestDTO;
import website.skillforge.be.entities.Account;
import website.skillforge.be.entities.Assignment;
import website.skillforge.be.entities.Rubric;
import website.skillforge.be.enums.status.Status;
import website.skillforge.be.repository.AssignmentRepository;
import website.skillforge.be.repository.RubricRepository;
import website.skillforge.be.util.AccountUtil;

import java.util.List;

@Service
public class RubricService {
    @Autowired
    private RubricRepository rubricRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    AccountUtil accountUtil;

    public Rubric createRubric(CreateRubricRequestDTO createRubricRequestDTO){
        Account account = accountUtil.getCurrentAccount();
        Assignment assignment = assignmentRepository.findAssignmentById(createRubricRequestDTO.getAssignment_id());
        Rubric rubric = new Rubric();
        rubric.setName(createRubricRequestDTO.getName());
        rubric.setDescription(createRubricRequestDTO.getDescription());
        rubric.setCreateDate(createRubricRequestDTO.getCreatedDate());
        rubric.setCreateBy(account);
        return rubric;
    }


    public Rubric getRubricById(Long id) {
        return rubricRepository.findRubricById(id);
    }

    public Rubric getRubricByName(String name) {

        return rubricRepository.findRubricByName(name);
    }

    public List<Rubric> getAllQuizzes() {

        return rubricRepository.findAll();
    }
}
