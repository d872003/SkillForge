package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CreateCriteriaRequestDTO;
import website.skillforge.be.dto.createDTO.CreateRubricRequestDTO;
import website.skillforge.be.dto.createDTO.CreateScoreRequestDTO;
import website.skillforge.be.dto.updateDTO.UpdateCriteriaDTO;
import website.skillforge.be.dto.updateDTO.UpdateScoreDTO;
import website.skillforge.be.entities.*;
import website.skillforge.be.enums.status.Status;
import website.skillforge.be.repository.CriteriaRepository;
import website.skillforge.be.repository.RubricRepository;
import website.skillforge.be.repository.ScoreRepository;
import website.skillforge.be.util.AccountUtil;

import java.util.List;
@Service
public class ScoreService {
    @Autowired
    private CriteriaRepository criteriaRepository;
    @Autowired
    private ScoreRepository scoreRepository;


    public Score createScore(CreateScoreRequestDTO createScoreRequestDTO){
        Criteria criteria = criteriaRepository.findCriteriaById(createScoreRequestDTO.getCriteria_id());
        Score score = new Score();
        score.setScore(createScoreRequestDTO.getScore());
        score.setComment(createScoreRequestDTO.getComment());
        score.setSubmissionDate(createScoreRequestDTO.getSubmissionDate());
        return score;
    }
    public Score updateScore(Long id, UpdateScoreDTO Score){
        Score oldScore = scoreRepository.findScoreById(id);
        if (oldScore != null){
            oldScore.setScore(Score.getScore());
            oldScore.setComment(Score.getComment());
            oldScore.setSubmissionDate(Score.getSubmissionDate());
            return scoreRepository.save(oldScore);
        }
        return null;
    }

    public void deleteScore (Long id){
         Score score = scoreRepository.findScoreById(id);
         scoreRepository.delete(score);
    }

    public Score getScoreById(Long id){
        return scoreRepository.findScoreById(id);
    }

    public List<Score> getAllScore() {
        return scoreRepository.findAll();
    }
}
