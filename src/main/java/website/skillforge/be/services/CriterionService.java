package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CreateCriterionRequestDTO;
import website.skillforge.be.entities.Criterion;
import website.skillforge.be.repository.CriterionRepository;
import website.skillforge.be.repository.RubricRepository;

import java.util.List;

@Service
public class CriterionService {
    @Autowired
    CriterionRepository criterionRepository;
    @Autowired
    RubricRepository rubricRepository;

    public Criterion createCriterion(CreateCriterionRequestDTO criterion) {
        Criterion newCriterion = new Criterion();
        newCriterion.setName(criterion.getName());
        newCriterion.setRubric(rubricRepository.findRubricById(criterion.getRubric_id()));
        return criterionRepository.save(newCriterion);
    }

    public Criterion deleteCriterion(long id) {
        Criterion criterion = criterionRepository.findCriterionById(id);
        criterionRepository.delete(criterion);
        return criterion;
    }

    public Criterion updateCriterion(long id, CreateCriterionRequestDTO criterion) {
        Criterion existingCriterion = criterionRepository.findCriterionById(id);
        if (existingCriterion == null) {
            return null;
        }
        existingCriterion.setName(criterion.getName());
        existingCriterion.setRubric(rubricRepository.findRubricById(criterion.getRubric_id()));
        return criterionRepository.save(existingCriterion);
    }

    public Criterion getCriterionById(long id) {
        return criterionRepository.findCriterionById(id);
    }

    public List<Criterion> getAllCriteria() {
        return criterionRepository.findAll();
    }

    public Criterion getCriterionByName(String name) {
        return criterionRepository.findCriterionByName(name);
    }
}
