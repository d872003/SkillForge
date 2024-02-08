package website.skillforge.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.Chapter;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    Chapter findChapterById(long id);

    Chapter findChapterByName(String name);
}
