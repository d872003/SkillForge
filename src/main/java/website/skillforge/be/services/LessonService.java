package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.CreateCategoryRequestDTO;
import website.skillforge.be.dto.CreateLessonRequestDTO;
import website.skillforge.be.entities.Category;
import website.skillforge.be.entities.Lesson;
import website.skillforge.be.repository.LessonRepository;

import java.util.List;
@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    public Lesson createLesson(CreateLessonRequestDTO createLessonRequestDTO) {
        Lesson lesson = new Lesson();
        lesson.setName(createLessonRequestDTO.getName());
        lesson.setDescription(createLessonRequestDTO.getDescription());
        return lesson;
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
            existingCategory.setName(lesson.getName());
            existingCategory.setDescription(lesson.getDescription());
            return lessonRepository.save(existingCategory);
        }
        return null;
    }

public List<Lesson> getAllCategories() {
        return lessonRepository.findAll();
    }

}
