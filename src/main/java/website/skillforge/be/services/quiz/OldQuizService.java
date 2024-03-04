package website.skillforge.be.services.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.entities.quiz.*;
import website.skillforge.be.repository.quizRepo.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class OldQuizService {
    @Autowired
    OldQuizRepository oldQuizRepository;
    @Autowired
    OldQuizQuestionRepository oldQuizQuestionRepository;
    @Autowired
    OldQuizAnswerRepository oldQuizAnswerRepository;
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuizQuestionRepository quizQuestionRepository;
    @Autowired
    QuizAnswerRepository quizAnswerRepository;

    public OldQuiz saveOldQuiz(Long id) {
        OldQuiz oldQuiz1 = new OldQuiz();
        Quiz quiz1 = quizRepository.findQuizById(id);
        oldQuiz1.setName(quiz1.getName());
        oldQuiz1.setDescription(quiz1.getDescription());
        oldQuiz1.setCreatedDate(quiz1.getCreatedDate());
        oldQuiz1.setLastUpdatedDate(quiz1.getLastUpdatedDate());
        return oldQuizRepository.save(oldQuiz1);
    }

    public OldQuizQuestion saveOldQuizQuestion(Long id) {
        OldQuizQuestion oldQuizQuestion1 = new OldQuizQuestion();
        QuizQuestion quizQuestion1 = quizQuestionRepository.findQuizQuestionById(id);
        oldQuizQuestion1.setQuestionNumber(quizQuestion1.getQuestionNumber());
        oldQuizQuestion1.setQuestionContent(quizQuestion1.getQuestionContent());
        oldQuizQuestion1.setQuestionScore(quizQuestion1.getQuestionScore());
        return oldQuizQuestionRepository.save(oldQuizQuestion1);
    }

    public List<OldQuizQuestion> saveOldQuizQuestions(Long id) {
        List<OldQuizQuestion> oldQuizQuestions = new ArrayList<>();
        List<QuizQuestion> quizQuestions = quizQuestionRepository.findQuizQuestionByQuiz_id(id);

        for (QuizQuestion quizQuestion : quizQuestions) {
            OldQuizQuestion oldQuizQuestion = saveOldQuizQuestion(quizQuestion.getId());
            oldQuizQuestion.setQuestionNumber(quizQuestion.getQuestionNumber());
            oldQuizQuestion.setQuestionContent(quizQuestion.getQuestionContent());
            oldQuizQuestion.setQuestionScore(quizQuestion.getQuestionScore());
            oldQuizQuestions.add(oldQuizQuestion);
        }
        return oldQuizQuestions;
    }

    public OldQuizAnswer saveOldQuizAnswer(Long id) {
        OldQuizAnswer oldQuizAnswer1 = new OldQuizAnswer();
        QuizAnswer quizAnswer1 = quizAnswerRepository.findQuizAnswerById(id);
        oldQuizAnswer1.setAnswerNumber(quizAnswer1.getAnswerNumber());
        oldQuizAnswer1.setAnswerContent(quizAnswer1.getAnswerContent());
        oldQuizAnswer1.setTrue(quizAnswer1.isTrue());
        return oldQuizAnswerRepository.save(oldQuizAnswer1);
    }

    public List<OldQuizAnswer> saveOldQuizAnswers(Long id) {
        List<OldQuizAnswer> oldQuizAnswers = new ArrayList<>();
        List<QuizAnswer> quizAnswers = quizAnswerRepository.findQuizAnswerByQuizQuestion_id(id);

        for (QuizAnswer quizAnswer : quizAnswers) {
            OldQuizAnswer oldQuizAnswer = saveOldQuizAnswer(quizAnswer.getId());
            oldQuizAnswer.setAnswerNumber(quizAnswer.getAnswerNumber());
            oldQuizAnswer.setAnswerContent(quizAnswer.getAnswerContent());
            oldQuizAnswer.setTrue(quizAnswer.isTrue());
            oldQuizAnswers.add(oldQuizAnswer);
        }
        return oldQuizAnswers;
    }

    public List<OldQuiz> getOldQuiz() {
        return oldQuizRepository.findAll();
    }

    public List<OldQuizQuestion> getOldQuizQuestion() {
        return oldQuizQuestionRepository.findAll();
    }

    public List<OldQuizAnswer> getOldQuizAnswer() {
        return oldQuizAnswerRepository.findAll();
    }


}
