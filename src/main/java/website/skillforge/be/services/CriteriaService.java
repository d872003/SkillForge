package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CreateCriteriaRequestDTO;
import website.skillforge.be.dto.createDTO.CreateRubricRequestDTO;
import website.skillforge.be.dto.updateDTO.UpdateCriteriaDTO;
import website.skillforge.be.entities.*;
import website.skillforge.be.enums.status.Status;
import website.skillforge.be.repository.CriteriaRepository;
import website.skillforge.be.repository.RubricRepository;
import website.skillforge.be.util.AccountUtil;

import java.util.List;
@Service
public class CriteriaService {

    @Autowired
    private CriteriaRepository criteriaRepository;
    @Autowired
    private RubricRepository rubricRepository;

    public Criteria createCriteria(CreateCriteriaRequestDTO createCriteriaRequestDTO){
        Rubric rubric = rubricRepository.findRubricById(createCriteriaRequestDTO.getRubricId());
        Criteria criteria = new Criteria();
        criteria.setName(createCriteriaRequestDTO.getName());
        criteria.setDescription(createCriteriaRequestDTO.getDescription());
        criteria.setCreatedDate(createCriteriaRequestDTO.getCreatedDate());
        criteria.setMaxScore(createCriteriaRequestDTO.getMaxScore());
        return criteria;
    }

    public  Criteria updateCriteria(long id, UpdateCriteriaDTO criteria){
        Criteria oldCriteria = criteriaRepository.findCriteriaById(id);
        if (oldCriteria != null){
            oldCriteria.setName(criteria.getName());
            oldCriteria.setDescription(criteria.getDescription());
            oldCriteria.setLastUpdateDate(criteria.getLastUpdateDate());
            oldCriteria.setMaxScore(criteria.getMaxScore());
            return criteriaRepository.save(oldCriteria);
        }
        return null;
    }


    public void deleteCriteria(Long id){
        Criteria criteria = criteriaRepository.findCriteriaById(id);
        criteriaRepository.delete(criteria);
    }

    public Criteria getCriteriaById(Long id) {

        return criteriaRepository.findCriteriaById(id);
    }

    public Criteria getCriteriaByName(String name) {

        return criteriaRepository.findCriteriaByName(name);
    }

    public List<Criteria> getAllCriteria() {

        return criteriaRepository.findAll();
    }
}
