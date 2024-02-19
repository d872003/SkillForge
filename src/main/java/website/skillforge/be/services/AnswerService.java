package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CreateAnswerRequestDTO;
import website.skillforge.be.entities.Answer;
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
        Answer answer = new Answer();
//        Question question = questionRepository.findQuestionByQuestionNumber(createAnswerRequest.getQuestionId());
        answer.setAnswer1(createAnswerRequest.getAnswer1());
        answer.setAnswer2(createAnswerRequest.getAnswer2());
        answer.setAnswer3(createAnswerRequest.getAnswer3());
        answer.setAnswer4(createAnswerRequest.getAnswer4());
        answer.setTrue(createAnswerRequest.getIsTrue());
//        answer.setQuestion(question);
        return answerRepository.save(answer);
    }

    public int deleteAnswerById(Long id) {
        answerRepository.deleteById(id);
        return 1;
    }

    public Answer updateAnswer(Long id, CreateAnswerRequestDTO answer) {
        Answer existingAnswer = answerRepository.findAnswerById(id);
        if (existingAnswer != null) {
            existingAnswer.setAnswer1(answer.getAnswer1());
            existingAnswer.setAnswer2(answer.getAnswer2());
            existingAnswer.setAnswer3(answer.getAnswer3());
            existingAnswer.setAnswer4(answer.getAnswer4());
            existingAnswer.setTrue(answer.getIsTrue());
            return answerRepository.save(existingAnswer);
        }
        return null;
    }

    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    public Answer getAnswerById(Long id) {
        return answerRepository.findAnswerById(id);
    }

}
