package website.skillforge.be.services.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CreateQuizRequestDTO;
import website.skillforge.be.dto.createDTO.GetAllQuizResponse;
import website.skillforge.be.entities.Lesson;
import website.skillforge.be.entities.quiz.Quiz;
import website.skillforge.be.repository.LessonRepository;
import website.skillforge.be.repository.quizRepo.QuizRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private LessonRepository lessonRepository;

    public Quiz createQuiz(CreateQuizRequestDTO createQuizRequestDTO) {
        Lesson lesson = lessonRepository.findLessonById(createQuizRequestDTO.getLesson_id());
        Date date = new Date();
        Quiz quiz = new Quiz();
        quiz.setName(createQuizRequestDTO.getName());
        quiz.setDescription(createQuizRequestDTO.getDescription());
        quiz.setCreatedDate(date);
        quiz.setLastUpdatedDate(date);
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
        Lesson lesson = lessonRepository.findLessonById(createQuizRequestDTO.getLesson_id());
        Date date = new Date();
        if (lesson == null) {
            return null;
        }
        Quiz quiz = quizRepository.findQuizById(id);
        quiz.setName(createQuizRequestDTO.getName());
        quiz.setDescription(createQuizRequestDTO.getDescription());
        quiz.setLastUpdatedDate(date);
        quiz.setLesson(lesson);
        quizRepository.save(quiz);
        return quiz;
    }

    public Quiz getQuizById(Long id) {
        return quizRepository.findQuizById(id);
    }

    public List<Quiz> getQuizByLessonId(Long id) {
        return quizRepository.findQuizByLesson_id(id);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public List<GetAllQuizResponse> getAllQuizDTOByLessonId(Long id) {
        List<Quiz> quizzes = quizRepository.findQuizByLesson_id(id);
        List<GetAllQuizResponse> quizDTOs = new ArrayList<>();
        for (Quiz quiz : quizzes) {
            GetAllQuizResponse quizDTO = new GetAllQuizResponse();
            quizDTO.setId(quiz.getId());
            quizDTO.setName(quiz.getName());
            quizDTO.setDescription(quiz.getDescription());
            quizDTO.setLesson_id(quiz.getLesson().getId());
            quizDTOs.add(quizDTO);
        }
        return quizDTOs;
    }


}
