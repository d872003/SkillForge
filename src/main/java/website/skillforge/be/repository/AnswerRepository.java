package website.skillforge.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.Answer;
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Answer findAnswerById(Long id);


}
