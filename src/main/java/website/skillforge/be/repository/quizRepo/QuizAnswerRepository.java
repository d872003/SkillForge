package website.skillforge.be.repository.quizRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.quizzes.QuizAnswer;

import java.util.List;

@Repository
public interface QuizAnswerRepository extends JpaRepository<QuizAnswer, Long> {
    QuizAnswer findQuizAnswerById(Long id);

    List<QuizAnswer> findQuizAnswerByQuizQuestion_id(Long id);


}
