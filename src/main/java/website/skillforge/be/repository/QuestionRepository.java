package website.skillforge.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Question findQuestionByQuestionNumber(int questionNumber);

    Question findQuestionById(Long id);

}
