package website.skillforge.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.Lesson;
@Repository


public interface LessonRepository extends JpaRepository<Lesson, Long> {

    Lesson findLessonById(long id);
    Lesson findLessonByName(String name);
}
