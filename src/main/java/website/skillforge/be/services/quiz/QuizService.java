package website.skillforge.be.services.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.UserAnswerDto;
import website.skillforge.be.dto.createDTO.quizDto.CheckDoQuizResponse;
import website.skillforge.be.entities.Lesson;
import website.skillforge.be.entities.UserAnswer;
import website.skillforge.be.entities.quiz.Quiz;
import website.skillforge.be.entities.quiz.QuizResult;
import website.skillforge.be.repository.LessonRepository;
import website.skillforge.be.repository.UserAnswerRepository;
import website.skillforge.be.repository.quizRepo.QuizAnswerRepository;
import website.skillforge.be.repository.quizRepo.QuizRepository;
import website.skillforge.be.repository.quizRepo.QuizResultRepository;
import website.skillforge.be.util.AccountUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    QuizResultRepository quizResultRepository;
    @Autowired
    QuizResultService quizResultService;
    @Autowired
    private AccountUtil accountUtil;
    @Autowired
    private UserAnswerRepository userAnswerRepository;
    @Autowired
    QuizAnswerRepository quizAnswerRepository;



    public Quiz deleteQuizById(Long id) {
        Quiz quiz = quizRepository.findQuizById(id);
        quizRepository.deleteById(id);
        return quiz;
    }


    public Quiz getQuizById(Long id) {

        return quizRepository.findQuizById(id);
    }

    public Quiz getQuizByLessonId2(Long id) {
        return quizRepository.findQuizByLesson_id(id);
    }

    static int checkDo = 0;
    public CheckDoQuizResponse getQuizByLessonId(Long id) {
        List<UserAnswer> userAnsIds = new ArrayList<>();
        CheckDoQuizResponse checkDoQuizResponse = new CheckDoQuizResponse();
        List<UserAnswerDto> userAnswerDtos = new ArrayList<>();
        Quiz quiz = quizRepository.findQuizByLesson_id(id);
        if ((quizResultRepository.findQuizResultByQuizId(quiz.getId()) != null)) {
            QuizResult quizResult = quizResultRepository.findFirstByDoById(accountUtil.getCurrentAccount().getId());
            if (quizResult != null) {
                checkDoQuizResponse.setIsDo(true);
            }
        } else {
            checkDoQuizResponse.setIsDo(false);
        }
        checkDoQuizResponse.setQuiz(quiz);
        if (checkDoQuizResponse.getIsDo() != null && checkDo == 1) {
            for (Long ansIds : quizResultService.getUserAns()) {
                UserAnswer userAnswer = new UserAnswer();
                userAnswer.setAnswerId(ansIds);
                userAnswer.setQuizId(quiz.getId());
                userAnswer.setTrue(quizAnswerRepository.findQuizAnswerById(ansIds).isTrue());
                userAnswer.setAccount(accountUtil.getCurrentAccount());
                userAnswerRepository.save(userAnswer);
            }
            checkDo = 0;
        }
        userAnsIds = userAnswerRepository.findUserAnswerByQuizIdAndAccountId(quiz.getId(), accountUtil.getCurrentAccount().getId());
        for (UserAnswer userAnswer : userAnsIds) {
            UserAnswerDto userAnswerDto = new UserAnswerDto();
            userAnswerDto.setId(userAnswer.getAnswerId());
            userAnswerDto.setTrue(userAnswer.isTrue());
            userAnswerDtos.add(userAnswerDto);
        }
        checkDoQuizResponse.setAnswerUser(userAnswerDtos);
        return checkDoQuizResponse;
    }
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }


}
