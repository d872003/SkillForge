package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CreateProgressDto;
import website.skillforge.be.entities.Chapter;
import website.skillforge.be.entities.Course;
import website.skillforge.be.entities.Lesson;
import website.skillforge.be.entities.Progress;
import website.skillforge.be.repository.ChapterRepository;
import website.skillforge.be.repository.CourseRepository;
import website.skillforge.be.repository.LessonRepository;
import website.skillforge.be.repository.ProgressRepository;
import website.skillforge.be.util.AccountUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProgressService {
    @Autowired
    private ProgressRepository progressRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private AccountUtil accountUtil;
    @Autowired
    private LessonRepository lessonRepository;

    public Progress createProgress(CreateProgressDto createProgressDto) {
        Progress progress = new Progress();
        Lesson lesson = lessonRepository.findLessonById(createProgressDto.getLessonId());
        progress.setLesson(lesson);
        progress.setAccount(accountUtil.getCurrentAccount());
        return progressRepository.save(progress);
    }

    public double percentCourse(long courseId) {
        double percent = 0;
        List<Progress> progress = progressRepository.findProgressByAccountId(accountUtil.getCurrentAccount().getId());
        if (progress != null) {
            Course course = courseRepository.findCourseById(courseId);
            int numOfLessons = 0;
            int numOfLessonDone = 0;
            List<Chapter> chapters = chapterRepository.findChapterByCourse_id(courseId);
            List<Lesson> lessons = new ArrayList<>();
            for (Chapter chapter : chapters) {
                int numOfLesson = lessonRepository.findLessonByChapter_id(chapter.getId()).size();
                numOfLessons += numOfLesson;
                lessons.addAll(lessonRepository.findLessonByChapter_id(chapter.getId()));
            }
            for (Lesson lesson : lessons) {
                for (Progress progress1 : progress) {
                    if (progress1.getLesson().getId() == lesson.getId()) {
                        numOfLessonDone++;
                    }
                }
            }
            percent = (double) numOfLessonDone / numOfLessons * 100;
        } else {
            return 0;
        }
        return percent;
    }

}
