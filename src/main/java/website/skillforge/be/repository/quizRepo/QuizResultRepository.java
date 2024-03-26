package website.skillforge.be.repository.quizRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.quizzes.QuizResult;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {

    QuizResult findQuizResultById(Long id);

    QuizResult findQuizResultByQuizId(Long quiz_id);

    QuizResult findFirstByDoById(Long account_id);


}
