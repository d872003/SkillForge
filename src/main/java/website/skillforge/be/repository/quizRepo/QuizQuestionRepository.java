package website.skillforge.be.repository.quizRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.quiz.QuizQuestion;

import java.util.List;

@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long> {

    QuizQuestion findQuizQuestionByQuestionNumber(int questionNumber);

    QuizQuestion findQuizQuestionById(Long id);

    List<QuizQuestion> findQuizQuestionByQuiz_id(Long id);
}
