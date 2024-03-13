package website.skillforge.be.services.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.quizDto.CreateQuizResultRequestDto;
import website.skillforge.be.entities.*;
import website.skillforge.be.entities.quiz.QuizResult;
import website.skillforge.be.repository.quizRepo.QuizResultRepository;
import website.skillforge.be.util.AccountUtil;
import java.util.Date;

@Service
public class
QuizResultService {
    @Autowired
    private QuizResultRepository quizResultRepository;
    @Autowired
    private AccountUtil accountUtil;

}
