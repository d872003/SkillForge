package website.skillforge.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.Video;
@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    Video findVideoById(long id);

    Video findVideoByName(String name);
}
