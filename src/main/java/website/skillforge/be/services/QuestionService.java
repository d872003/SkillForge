package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CreateQuestionRequestDTO;
import website.skillforge.be.entities.Question;
import website.skillforge.be.entities.Quiz;
import website.skillforge.be.repository.QuestionRepository;
import website.skillforge.be.repository.QuizRepository;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuizRepository quizRepository;

    public Question createQuestion(CreateQuestionRequestDTO createQuestionRequest) {
        Question question = new Question();
        Quiz quiz = quizRepository.findQuizById(createQuestionRequest.getQuiz_id());
        question.setQuestionNumber(createQuestionRequest.getQuestionNumber());
        question.setDescription(createQuestionRequest.getDescription());
        question.setQuiz(quiz);
        return questionRepository.save(question);
    }

    public void deleteQuestionById(Long id) {
        Question question = questionRepository.findQuestionById(id);
        questionRepository.delete(question);
    }

    public Question updateQuestion(Long id, CreateQuestionRequestDTO createQuestionRequest) {
        Question question = questionRepository.findQuestionById(id);
        question.setQuestionNumber(createQuestionRequest.getQuestionNumber());
        question.setDescription(createQuestionRequest.getDescription());
        question.setQuiz(quizRepository.findQuizById(createQuestionRequest.getQuiz_id()));
        return questionRepository.save(question);
    }


    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findQuestionById(id);
    }


}
