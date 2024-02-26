package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CreateQuizResultRequestDto;
import website.skillforge.be.entities.*;
import website.skillforge.be.enums.status.CourseStatus;
import website.skillforge.be.repository.QuizRepository;
import website.skillforge.be.repository.QuizResultRepository;
import website.skillforge.be.util.AccountUtil;

import java.util.Date;
import java.util.List;

@Service
public class QuizResultService {
    @Autowired
    private QuizResultRepository quizResultRepository;
    @Autowired
    private QuizRepository quizRepository;
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
