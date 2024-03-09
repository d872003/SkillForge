package website.skillforge.be.services.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.quizDto.CreateAnswerRequestDTO;
import website.skillforge.be.entities.quiz.QuizAnswer;
import website.skillforge.be.entities.quiz.QuizQuestion;
import website.skillforge.be.repository.quizRepo.QuizAnswerRepository;
import website.skillforge.be.repository.quizRepo.QuizQuestionRepository;

import java.util.List;

@Service
public class QuizAnswerService {
    @Autowired
    private QuizAnswerRepository quizAnswerRepository;
    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    public QuizAnswer createAnswer(CreateAnswerRequestDTO createAnswerRequest) {
        QuizQuestion question = quizQuestionRepository.findQuizQuestionById(createAnswerRequest.getQuizQuestion_id());
        QuizAnswer answer = new QuizAnswer();
        answer.setAnswerNumber(createAnswerRequest.getAnswerNumber());
        answer.setAnswerContent(createAnswerRequest.getAnswerContent());
        answer.setTrue(createAnswerRequest.getIsTrue());
        answer.setQuizQuestion(question);
        return quizAnswerRepository.save(answer);
    }

    public QuizAnswer getAnswerById(Long id) {
        return quizAnswerRepository.findQuizAnswerById(id);
    }

    public void deleteAnswerById(Long id) {
        quizAnswerRepository.deleteById(id);
    }

    public QuizAnswer updateAnswer(Long id, CreateAnswerRequestDTO createAnswerRequest) {
        QuizAnswer answer = quizAnswerRepository.findQuizAnswerById(id);
        if (answer != null) {
            answer.setAnswerNumber(createAnswerRequest.getAnswerNumber());
            answer.setAnswerContent(createAnswerRequest.getAnswerContent());
            answer.setTrue(createAnswerRequest.getIsTrue());
            answer.setQuizQuestion(quizQuestionRepository.findQuizQuestionById(createAnswerRequest.getQuizQuestion_id()));
            return quizAnswerRepository.save(answer);
        }
        return null;
    }

    public List<QuizAnswer> getAllAnswers() {
        return quizAnswerRepository.findAll();
    }

}
