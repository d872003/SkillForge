package website.skillforge.be.repository.quizRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.quiz.OldQuizQuestion;

@Repository
public interface OldQuizQuestionRepository extends JpaRepository<OldQuizQuestion, Long> {
    OldQuizQuestion findOldQuizQuestionById(Long id);

}
