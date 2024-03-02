package website.skillforge.be.services.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CreateQuizResultRequestDto;
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

    public QuizResult createQuizResult(CreateQuizResultRequestDto createQuizResultRequest) {
        Account account = accountUtil.getCurrentAccount();
        Date date = new Date();
        QuizResult quizResult = new QuizResult();
        quizResult.setScore(createQuizResultRequest.getScore());
        quizResult.setTrueAnswerNumber(createQuizResultRequest.getTrueAnswerNumber());
        quizResult.setFalseAnswerNumber(createQuizResultRequest.getFalseAnswerNumber());
        quizResult.setDate(date);
        quizResult.setDoBy(account);
        return quizResultRepository.save(quizResult);
    }

    public QuizResult getQuizResult(Long id) {
        return quizResultRepository.findQuizResultById(id);
    }

}
