package website.skillforge.be.repository.quizRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.quizzes.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    Quiz findQuizById(long id);

    Quiz findQuizByLesson_id(long id);

}
