package website.skillforge.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.QuizAnswer;
@Repository
public interface QuizAnswerRepository extends JpaRepository<QuizAnswer, Long> {
    QuizAnswer findAnswerById(Long id);


}
