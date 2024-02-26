package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CreateQuestionRequestDTO;
import website.skillforge.be.entities.QuizQuestion;
import website.skillforge.be.entities.Quiz;
import website.skillforge.be.repository.QuizQuestionRepository;
import website.skillforge.be.repository.QuizRepository;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuizQuestionRepository quizQuestionRepository;
    @Autowired
    private QuizRepository quizRepository;

    public QuizQuestion createQuestion(CreateQuestionRequestDTO createQuestionRequest) {
        QuizQuestion question = new QuizQuestion();
        Quiz quiz = quizRepository.findQuizById(createQuestionRequest.getQuiz_id());
        question.setQuestionNumber(createQuestionRequest.getQuestionNumber());
        question.setQuestionContent(createQuestionRequest.getQuestionContent());
        question.setQuiz(quiz);
        return quizQuestionRepository.save(question);
    }

    public void deleteQuestionById(Long id) {
        QuizQuestion question = quizQuestionRepository.findQuestionById(id);
        quizQuestionRepository.delete(question);
    }

    public QuizQuestion updateQuestion(Long id, CreateQuestionRequestDTO createQuestionRequest) {
        QuizQuestion question = quizQuestionRepository.findQuestionById(id);
        if (question == null) {
            return null;
        }
        question.setQuestionNumber(createQuestionRequest.getQuestionNumber());
        question.setQuestionContent(createQuestionRequest.getQuestionContent());
        question.setQuiz(quizRepository.findQuizById(createQuestionRequest.getQuiz_id()));
        return quizQuestionRepository.save(question);
    }


    public List<QuizQuestion> getAllQuestions() {
        return quizQuestionRepository.findAll();
    }

    public QuizQuestion getQuestionById(Long id) {
        return quizQuestionRepository.findQuestionById(id);
    }


}
