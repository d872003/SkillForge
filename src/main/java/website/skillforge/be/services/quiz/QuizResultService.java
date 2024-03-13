package website.skillforge.be.services.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.quizDto.CreateQuizResultRequestDto;
import website.skillforge.be.dto.createDTO.quizDto.GetQuizAnswerRequestDto;
import website.skillforge.be.entities.*;
import website.skillforge.be.entities.quiz.*;
import website.skillforge.be.repository.quizRepo.*;
import website.skillforge.be.util.AccountUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class
QuizResultService {
    @Autowired
    private QuizResultRepository quizResultRepository;
    @Autowired
    private QuizAnswerRepository quizAnswerRepository;
    @Autowired
    private QuizQuestionRepository quizQuestionRepository;
    @Autowired
    private OldQuizRepository oldQuizRepository;
    @Autowired
    private OldQuizQuestionRepository oldQuizQuestionRepository;
    @Autowired
    private OldQuizAnswerRepository oldQuizAnswerRepository;
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private AccountUtil accountUtil;

    public QuizResult createQuizResult(GetQuizAnswerRequestDto answerRequestDto) {
        QuizResult quizResult = new QuizResult();
        Account account = accountUtil.getCurrentAccount();
        quizResult.setScore(scoring(quizResult, answerRequestDto));
        quizResult.setDoBy(account);
        quizResult.setDate(new Date());
        quizResultRepository.save(quizResult);
        OldQuiz oldQuiz = new OldQuiz();
        Quiz quiz = quizRepository.findQuizById(answerRequestDto.getQuizId());
        List<OldQuizQuestion> oldQuizQuestions = new ArrayList<>();
        for (QuizQuestion question : quiz.getQuizQuestion()) {
            OldQuizQuestion oldQuizQuestion = new OldQuizQuestion();
            oldQuizQuestion.setQuestionNumber(question.getQuestionNumber());
            oldQuizQuestion.setQuestionContent(question.getQuestionContent());
            oldQuizQuestion.setQuestionScore(question.getQuestionScore());
            oldQuizQuestion.setOldQuiz(oldQuiz);
            List<OldQuizAnswer> oldQuizAnswers = new ArrayList<>();
            for (QuizAnswer quizAnswer : question.getQuizAnswers()) {
                OldQuizAnswer oldQuizAnswer = new OldQuizAnswer();
                oldQuizAnswer.setAnswerNumber(quizAnswer.getAnswerNumber());
                oldQuizAnswer.setAnswerContent(quizAnswer.getAnswerContent());
                oldQuizAnswer.setAnswerScore(quizAnswer.getAnswerScore());
                oldQuizAnswer.setTrue(quizAnswer.isTrue());
                oldQuizAnswer.setOldQuizQuestion(oldQuizQuestion);
                oldQuizAnswerRepository.save(oldQuizAnswer);
            }
            oldQuizQuestion.setOldQuizAnswers(oldQuizAnswers);
            oldQuizQuestionRepository.save(oldQuizQuestion);
            oldQuizQuestions.add(oldQuizQuestion);
        }
        oldQuiz.setOldQuizQuestion(oldQuizQuestions);
        oldQuiz.setCreatedDate(new Date());
        oldQuiz.setLessonName(quiz.getLesson().getName());
        oldQuiz.setChapterName(quiz.getLesson().getChapter().getName());
        oldQuiz.setCourseName(quiz.getLesson().getChapter().getCourse().getName());
        oldQuiz.setDoBy(account);
        oldQuizRepository.save(oldQuiz);
        return quizResult;
    }

    public Double getScoreAnswer(QuizAnswer quizAnswer) {
        QuizQuestion quiz = quizQuestionRepository.findQuizQuestionById(quizAnswer.getQuizQuestion().getId());
        quizAnswer.setAnswerScore(quiz.getQuestionScore());
        return quizAnswer.getAnswerScore();
    }

    public Double scoring(QuizResult quizResult, GetQuizAnswerRequestDto answerRequestDto) {
        double score = 0;
        int trueAnswerNumber = quizResult.getTrueAnswerNumber();
        int falseAnswerNumber = quizResult.getFalseAnswerNumber();
        for (Long userAns : answerRequestDto.getAnswerIds()) {
            QuizAnswer quizAnswer = quizAnswerRepository.findQuizAnswerById(userAns);
            if (quizAnswer.isTrue()) {
                score += getScoreAnswer(quizAnswer);
                trueAnswerNumber++;
            } else {
                falseAnswerNumber++;
            }
        }
        quizResult.setTrueAnswerNumber(trueAnswerNumber);
        quizResult.setFalseAnswerNumber(falseAnswerNumber);
        return score;
    }

    public QuizResult getQuizResult(Long id) {
        return quizResultRepository.findQuizResultById(id);
    }
}
