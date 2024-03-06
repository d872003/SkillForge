package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CreateChapterRequestDTO;
import website.skillforge.be.entities.Account;
import website.skillforge.be.entities.Chapter;
import website.skillforge.be.entities.Course;
import website.skillforge.be.repository.ChapterRepository;
import website.skillforge.be.repository.CourseRepository;
import website.skillforge.be.util.AccountUtil;

import java.util.ArrayList;
import java.util.Date;
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
        ;
        Account account = accountUtil.getCurrentAccount();
        Date date = new Date();
        Course course = courseRepository.findCourseById(createChapterRequestDTO.getCourse_id());
        Chapter chapter = new Chapter();
        chapter.setName(createChapterRequestDTO.getName());
        chapter.setDescription(createChapterRequestDTO.getDescription());
        chapter.setFreeChapter(createChapterRequestDTO.isFreeChapter());
        chapter.setCreatedDate(date);
        chapter.setLastUpdatedDate(date);
        chapter.setCourse(course);
        chapter.setCreateBy(account);
        return chapterRepository.save(chapter);
    }

    public List<Chapter> CreateChapters(List<CreateChapterRequestDTO> createChapterRequestDTO) {
        List<Chapter> chapters = new java.util.ArrayList<>();
        for (CreateChapterRequestDTO createChapterRequestDTO1 : createChapterRequestDTO) {
            chapters.add(CreateChapter(createChapterRequestDTO1));
        }
        return chapters;
    }

    public Chapter DeleteChapterById(Long id) {
        Chapter chapter = chapterRepository.findChapterById(id);
        chapterRepository.delete(chapter);
        return chapter;
    }

    public Chapter UpdateChapterById(Long id, CreateChapterRequestDTO updateChapterDTO) {
        Chapter chapter = chapterRepository.findChapterById(id);
        if (chapter == null) {
            return null;
        }
        Date date = new Date();
        chapter.setName(updateChapterDTO.getName());
        chapter.setDescription(updateChapterDTO.getDescription());
        chapter.setCourse(courseRepository.findCourseById(updateChapterDTO.getCourse_id()));
        chapter.setLastUpdatedDate(date);
        chapter.setFreeChapter(updateChapterDTO.isFreeChapter());
        return chapterRepository.save(chapter);
    }

    public List<Chapter> UpdateChaptersById(List<Long> ids, List<CreateChapterRequestDTO> updateChapterDTO) {
        List<Chapter> chapters = new java.util.ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            chapters.add(UpdateChapterById(ids.get(i), updateChapterDTO.get(i)));
        }
        return chapters;
    }

    public List<Chapter> UpdateChaptersByCourseId(Long courseId, CreateChapterRequestDTO updateChapterDTO) {
        List<Chapter> chapters = chapterRepository.findChapterByCourse_id(courseId);
        if (chapters == null) {
            return null;
        }
        Date date = new Date();
        for (int i = 0; i < chapters.size(); i++) {
            chapters.get(i).setName(updateChapterDTO.getName());
            chapters.get(i).setDescription(updateChapterDTO.getDescription());
            chapters.get(i).setCourse(courseRepository.findCourseById(courseId));
            chapters.get(i).setLastUpdatedDate(date);
            chapters.get(i).setFreeChapter(updateChapterDTO.isFreeChapter());
        }
        return chapterRepository.saveAll(chapters);
    }

    public Chapter GetChapterById(Long id) {
        return chapterRepository.findChapterById(id);
    }

    public List<Chapter> GetChaptersByCourseId(Long id) {
        return chapterRepository.findChapterByCourse_id(id);
    }
    public Chapter GetChapterByName(String name) {
        return chapterRepository.findChapterByName(name);
    }
    public List<Chapter> GetAllChapters() {
        return chapterRepository.findAll();
    }

}
