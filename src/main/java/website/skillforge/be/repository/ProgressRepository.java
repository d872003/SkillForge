package website.skillforge.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.Progress;

import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
    Progress findProgressByLessonId(Long userId);

    List<Progress> findProgressByAccountId(Long accountId);

    List<Progress> findProgressByCourseIdAndAccountId(Long courseId, Long accountId);

    Progress findProgressByLessonIdAndAccountId(Long lessonId, Long accountId);
}
