package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CreateAnswerRequestDTO;
import website.skillforge.be.entities.Answer;
import website.skillforge.be.entities.Question;
import website.skillforge.be.repository.AnswerRepository;
import website.skillforge.be.repository.QuestionRepository;

import java.util.List;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    public Answer createAnswer(CreateAnswerRequestDTO createAnswerRequest) {
        Question question = questionRepository.findQuestionById(createAnswerRequest.getQuestionId());
        Answer answer = new Answer();
        answer.setAnswerNumber(createAnswerRequest.getAnswerNumber());
        answer.setAnswerContent(createAnswerRequest.getAnswerContent());
        answer.setTrue(createAnswerRequest.getIsTrue());
        answer.setQuestion(question);
        return answerRepository.save(answer);
    }

    public Answer getAnswerById(Long id) {
        return answerRepository.findAnswerById(id);
    }

    public void deleteAnswerById(Long id) {
        answerRepository.deleteById(id);
    }

    public Answer updateAnswer(Long id, CreateAnswerRequestDTO createAnswerRequest) {
        Answer answer = answerRepository.findAnswerById(id);
        if (answer != null) {
            answer.setAnswerNumber(createAnswerRequest.getAnswerNumber());
            answer.setAnswerContent(createAnswerRequest.getAnswerContent());
            answer.setTrue(createAnswerRequest.getIsTrue());
            answer.setQuestion(questionRepository.findQuestionById(createAnswerRequest.getQuestionId()));
            return answerRepository.save(answer);
        }
        return null;
    }

    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

}
