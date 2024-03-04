package website.skillforge.be.repository.quizRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.quiz.OldQuizAnswer;

@Repository
public interface OldQuizAnswerRepository extends JpaRepository<OldQuizAnswer, Long> {

    OldQuizAnswer findOldQuizAnswerById(Long id);
}
