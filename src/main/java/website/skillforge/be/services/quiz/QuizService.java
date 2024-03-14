package website.skillforge.be.services.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.quizDto.CheckDoQuizResponse;
import website.skillforge.be.dto.createDTO.quizDto.CreateQuizRequestDTO;
import website.skillforge.be.dto.createDTO.quizDto.GetAllQuizResponse;
import website.skillforge.be.entities.Lesson;
import website.skillforge.be.entities.quiz.Quiz;
import website.skillforge.be.repository.LessonRepository;
import website.skillforge.be.repository.quizRepo.QuizRepository;
import website.skillforge.be.repository.quizRepo.QuizResultRepository;
import website.skillforge.be.util.AccountUtil;

import java.util.Date;
import java.util.List;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    QuizResultRepository quizResultRepository;
    @Autowired
    QuizResultService quizResultService;
    @Autowired
    private AccountUtil accountUtil;
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

    public Quiz getQuizByLessonId2(Long id) {
        return quizRepository.findQuizByLesson_id(id);
    }
    public CheckDoQuizResponse getQuizByLessonId(Long id) {
        CheckDoQuizResponse checkDoQuizResponse = new CheckDoQuizResponse();
        Quiz quiz = quizRepository.findQuizByLesson_id(id);
        if (quizResultRepository.findQuizResultByDoByIdAndQuizId(accountUtil.getCurrentAccount().getId(), quiz.getId()) != null) {
            checkDoQuizResponse.setIsDo(true);
        }
        checkDoQuizResponse.setQuiz(quiz);
        checkDoQuizResponse.setAnswerIds(quizResultService.getUserAns());
        return checkDoQuizResponse;
    }
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public GetAllQuizResponse getAllQuizDTOByLessonId(Long id) {
        Quiz quiz = quizRepository.findQuizByLesson_id(id);
        if (quiz == null) {
            return null;
        }
        GetAllQuizResponse quizDTO = new GetAllQuizResponse();
        quizDTO.setId(quiz.getId());
        quizDTO.setName(quiz.getName());
        quizDTO.setDescription(quiz.getDescription());
        quizDTO.setLesson_id(quiz.getLesson().getId());
        return quizDTO;
    }
}
