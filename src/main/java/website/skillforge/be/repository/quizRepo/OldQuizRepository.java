package website.skillforge.be.repository.quizRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.quiz.OldQuiz;

@Repository
public interface OldQuizRepository extends JpaRepository<OldQuiz, Long> {

    OldQuiz findOldQuizById(long id);

    OldQuiz findOldQuizByName(String name);
}
