package website.skillforge.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    Quiz findQuizById(long id);

    Quiz findQuizByName(String name);

}
