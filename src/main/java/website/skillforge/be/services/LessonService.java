package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CreateLessonRequestDTO;
import website.skillforge.be.entities.*;
import website.skillforge.be.repository.ChapterRepository;
import website.skillforge.be.repository.LessonRepository;
import website.skillforge.be.util.AccountUtil;

import java.util.List;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    AccountUtil accountUtil;

    public Lesson createLesson(CreateLessonRequestDTO createLessonRequestDTO) {
        Account account = accountUtil.getCurrentAccount();
        Chapter chapter = chapterRepository.findChapterById(createLessonRequestDTO.getChapter_id());
        Lesson lesson = new Lesson();
        lesson.setName(createLessonRequestDTO.getName());
        lesson.setVideoLink(createLessonRequestDTO.getVideoLink());
        lesson.setDescription(createLessonRequestDTO.getDescription());
        lesson.setCreatedDate(createLessonRequestDTO.getCreatedDate());
        lesson.setChapter(chapter);
        lesson.setCreateBy(account);
        return lessonRepository.save(lesson);
    }

    public Lesson findLessonById(Long id) {
        return lessonRepository.findLessonById(id);
    }

    public Lesson findLessonByName(String name) {
        return lessonRepository.findLessonByName(name);
    }


    public int deleteLessonById(Long id) {
        lessonRepository.deleteById(id);
        return 1;
    }

    public Lesson updateLesson(Long id, CreateLessonRequestDTO lesson) {
        Lesson existingCategory = lessonRepository.findLessonById(id);
        if (existingCategory != null) {
            Chapter chapter = chapterRepository.findChapterById(lesson.getChapter_id());
            existingCategory.setName(lesson.getName());
            existingCategory.setDescription(lesson.getDescription());
            existingCategory.setVideoLink(lesson.getVideoLink());
            existingCategory.setLastUpdatedDate(lesson.getLastUpdatedDate());
            existingCategory.setChapter(chapter);
            return lessonRepository.save(existingCategory);
        }
        return null;
    }

    public List<Lesson> getAllLesson() {
        return lessonRepository.findAll();
    }

}
