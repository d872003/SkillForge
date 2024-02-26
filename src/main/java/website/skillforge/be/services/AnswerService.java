package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CreateAnswerRequestDTO;
import website.skillforge.be.entities.QuizAnswer;
import website.skillforge.be.entities.QuizQuestion;
import website.skillforge.be.repository.QuizAnswerRepository;
import website.skillforge.be.repository.QuizQuestionRepository;

import java.util.List;

@Service
public class AnswerService {
    @Autowired
    private QuizAnswerRepository quizAnswerRepository;
    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    public QuizAnswer createAnswer(CreateAnswerRequestDTO createAnswerRequest) {
        QuizQuestion question = quizQuestionRepository.findQuestionById(createAnswerRequest.getQuestionId());
        QuizAnswer answer = new QuizAnswer();
        answer.setAnswerNumber(createAnswerRequest.getAnswerNumber());
        answer.setAnswerContent(createAnswerRequest.getAnswerContent());
        answer.setTrue(createAnswerRequest.getIsTrue());
        answer.setQuizQuestion(question);
        return quizAnswerRepository.save(answer);
    }

    public QuizAnswer getAnswerById(Long id) {
        return quizAnswerRepository.findAnswerById(id);
    }

    public void deleteAnswerById(Long id) {
        quizAnswerRepository.deleteById(id);
    }

    public QuizAnswer updateAnswer(Long id, CreateAnswerRequestDTO createAnswerRequest) {
        QuizAnswer answer = quizAnswerRepository.findAnswerById(id);
        if (answer != null) {
            answer.setAnswerNumber(createAnswerRequest.getAnswerNumber());
            answer.setAnswerContent(createAnswerRequest.getAnswerContent());
            answer.setTrue(createAnswerRequest.getIsTrue());
            answer.setQuizQuestion(quizQuestionRepository.findQuestionById(createAnswerRequest.getQuestionId()));
            return quizAnswerRepository.save(answer);
        }
        return null;
    }

    public List<QuizAnswer> getAllAnswers() {
        return quizAnswerRepository.findAll();
    }

}
