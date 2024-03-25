package website.skillforge.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.courses.Feedback;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    Feedback findFeedbackById(long id);

    List<Feedback> findFeedbackByCourseId(long id);

    List<Feedback> findFeedbackByAccountId(long accountId);
}
