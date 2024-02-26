package website.skillforge.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.QuizQuestion;

@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long> {

    QuizQuestion findQuestionByQuestionNumber(int questionNumber);

    QuizQuestion findQuestionById(Long id);

}
