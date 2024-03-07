package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CreateLessonRequestDTO;
import website.skillforge.be.dto.createDTO.GetAllLessonResponse;
import website.skillforge.be.entities.*;
import website.skillforge.be.repository.ChapterRepository;
import website.skillforge.be.repository.LessonRepository;
import website.skillforge.be.util.AccountUtil;

import java.util.ArrayList;
import java.util.Date;
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
        Date date = new Date();
        Chapter chapter = chapterRepository.findChapterById(createLessonRequestDTO.getChapter_id());
        Lesson lesson = new Lesson();
        lesson.setName(createLessonRequestDTO.getName());
        lesson.setVideoLink(createLessonRequestDTO.getVideoLink());
        lesson.setDescription(createLessonRequestDTO.getDescription());
        lesson.setCreatedDate(date);
        lesson.setLastUpdatedDate(date);
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
        Lesson lesson1 = lessonRepository.findLessonById(id);
        if (lesson1 == null) {
            return null;
        }
        Date date = new Date();
        lesson1.setName(lesson.getName());
        lesson1.setDescription(lesson.getDescription());
        lesson1.setVideoLink(lesson.getVideoLink());
        lesson1.setLastUpdatedDate(date);
        return lessonRepository.save(lesson1);
    }

    public List<Lesson> UpdateLessonByChapterId(Long id, CreateLessonRequestDTO updateLessonDTO) {
        List<Lesson> lessons = lessonRepository.findLessonByChapter_id(id);
        if (lessons == null) {
            return null;
        }
        Date date = new Date();
        for (int i = 0; i < lessons.size(); i++) {
            lessons.get(i).setName(updateLessonDTO.getName());
            lessons.get(i).setDescription(updateLessonDTO.getDescription());
            lessons.get(i).setVideoLink(updateLessonDTO.getVideoLink());
            lessons.get(i).setLastUpdatedDate(date);
        }
        return lessonRepository.saveAll(lessons);

    }


    public List<Lesson> getAllLesson() {
        return lessonRepository.findAll();
    }

    public List<Lesson> getAllLessonByChapterId(Long id) {
        return lessonRepository.findLessonByChapter_id(id);
    }

    public List<GetAllLessonResponse> getAllLessonDTOByChapterId(Long id) {
        List<Lesson> lessons = lessonRepository.findLessonByChapter_id(id);
        List<GetAllLessonResponse> lessonDTOs = new ArrayList<>();
        for (Lesson lesson : lessons) {
            GetAllLessonResponse lessonDTO = new GetAllLessonResponse();
            lessonDTO.setId(lesson.getId());
            lessonDTO.setName(lesson.getName());
            lessonDTO.setDescription(lesson.getDescription());
            lessonDTO.setVideoLink(lesson.getVideoLink());
            lessonDTO.setChapter_id(lesson.getChapter().getId());
            lessonDTOs.add(lessonDTO);
        }
        return lessonDTOs;
    }

}
