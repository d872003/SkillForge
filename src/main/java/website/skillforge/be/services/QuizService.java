package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.CreateQuizRequestDTO;
import website.skillforge.be.entities.Lesson;
import website.skillforge.be.entities.Quiz;
import website.skillforge.be.repository.LessonRepository;
import website.skillforge.be.repository.QuizRepository;

import java.util.List;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private LessonRepository lessonRepository;

    public Quiz createQuiz(CreateQuizRequestDTO createQuizRequestDTO) {
        Lesson lesson = lessonRepository.findLessonById(createQuizRequestDTO.getLesson_id());
        Quiz quiz = new Quiz();
        quiz.setName(createQuizRequestDTO.getName());
        quiz.setDescription(createQuizRequestDTO.getDescription());
        quiz.setLesson(lesson);
        quizRepository.save(quiz);
        return quiz;
    }

    public Quiz deleteQuizById(Long id) {
        Quiz quiz = quizRepository.findQuizById(id);
        quizRepository.deleteById(id);
        return quiz;
    }

    public Quiz updateQuiz(Long id, CreateQuizRequestDTO createQuizRequestDTO) {
        Quiz quiz = quizRepository.findQuizById(id);
        quiz.setName(createQuizRequestDTO.getName());
        quiz.setDescription(createQuizRequestDTO.getDescription());
        quizRepository.save(quiz);
        return quiz;
    }

    public Quiz getQuizById(Long id) {
        return quizRepository.findQuizById(id);
    }

    public Quiz getQuizByName(String name) {
        return quizRepository.findQuizByName(name);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }


}
