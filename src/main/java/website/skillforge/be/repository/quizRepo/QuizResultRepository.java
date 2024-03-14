package website.skillforge.be.repository.quizRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.quiz.QuizResult;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {

    QuizResult findQuizResultById(Long id);

    QuizResult findQuizResultByAccount_id(Long id);

}
