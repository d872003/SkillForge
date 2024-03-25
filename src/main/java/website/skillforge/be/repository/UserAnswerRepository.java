package website.skillforge.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.courses.UserAnswer;

import java.util.List;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    List<UserAnswer> findUserAnswerByQuizId(Long id); // <>

    List<UserAnswer> findUserAnswerByAccountId(Long userId);

    List<UserAnswer> findUserAnswerByQuizIdAndAccountId(Long quizId, Long accountId);
}
