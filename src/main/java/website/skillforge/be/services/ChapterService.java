package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.CreateChapterRequestDTO;
import website.skillforge.be.entities.Account;
import website.skillforge.be.entities.Category;
import website.skillforge.be.entities.Chapter;
import website.skillforge.be.entities.Course;
import website.skillforge.be.repository.ChapterRepository;
import website.skillforge.be.repository.CourseRepository;
import website.skillforge.be.util.AccountUtil;

import java.util.List;

@Service

public class ChapterService {
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    AccountUtil accountUtil;

    public Chapter CreateChapter(CreateChapterRequestDTO createChapterRequestDTO) {
        Account account = accountUtil.getCurrentAccount();
        Course course = courseRepository.findCourseById(createChapterRequestDTO.getCourse_id());
        Chapter chapter = new Chapter();
        chapter.setName(createChapterRequestDTO.getName());
        chapter.setDescription(createChapterRequestDTO.getDescription());
        chapter.setFreeChapter(createChapterRequestDTO.isFreeChapter());
        chapter.setCourse(course);
        chapter.setCreateBy(account);
        return chapterRepository.save(chapter);
    }
    public Chapter DeleteChapter(Long id) {
        Chapter chapter = chapterRepository.findChapterById(id);
        chapterRepository.delete(chapter);
        return chapter;
    }
    public Chapter UpdateChapter(Long id, CreateChapterRequestDTO createChapterRequestDTO) {
        Chapter chapter = chapterRepository.findChapterById(id);
        chapter.setName(createChapterRequestDTO.getName());
        chapter.setDescription(createChapterRequestDTO.getDescription());
        return chapterRepository.save(chapter);
    }
    public Chapter GetChapter(Long id) {
        return chapterRepository.findChapterById(id);
    }
    public Chapter GetChapterByName(String name) {
        return chapterRepository.findChapterByName(name);
    }
    public List<Chapter> GetAllChapters() {
        return chapterRepository.findAll();
    }

}
