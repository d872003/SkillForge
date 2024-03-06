package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.CourseDetailResponse;
import website.skillforge.be.dto.createDTO.CreateCourseRequestDTO;
import website.skillforge.be.entities.*;
import website.skillforge.be.entities.quiz.Quiz;
import website.skillforge.be.enums.status.CourseStatus;
import website.skillforge.be.repository.CategoryRepository;
import website.skillforge.be.repository.CourseRepository;

import website.skillforge.be.services.quiz.QuizService;
import website.skillforge.be.util.AccountUtil;

import java.util.Date;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ChapterService chapterService;
    @Autowired
    LessonService lessonService;
    @Autowired
    QuizService quizService;

    @Autowired
    AccountUtil accountUtil;

    public Course createCourse(CreateCourseRequestDTO course) {
        Account account = accountUtil.getCurrentAccount();
        Date date = new Date();
        Category category = categoryRepository.findCategoryById(course.getCategoryId());
        Course newCourse = new Course();
        newCourse.setName(course.getName());
        newCourse.setPrice(course.getPrice());
        newCourse.setPictureLink(course.getPictureLink());
        newCourse.setDescription(course.getDescription());
        newCourse.setCreatedDate(date);
        newCourse.setLastUpdatedDate(date);
        newCourse.setCourseStatus(CourseStatus.DRAFT);
        newCourse.setCategory(category);
        newCourse.setCreateBy(account);
        return courseRepository.save(newCourse);
    }

    public void deleteCourseById(Long id) {
        Course course = courseRepository.findCourseById(id);
        course.setCourseStatus(CourseStatus.DELETED);
        courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        return courseRepository.findCourseById(id);
    }

    public Course getCourseByName(String name) {
        return courseRepository.findCourseByName(name);
    }

    public Course updateCourse(Long id, CreateCourseRequestDTO course) {
        Course existingCourse = courseRepository.findCourseById(id);
        if (existingCourse != null) {
            Date date = new Date();
            Category category = categoryRepository.findCategoryById(course.getCategoryId());
            existingCourse.setName(course.getName());
            existingCourse.setPrice(course.getPrice());
            existingCourse.setPictureLink(course.getPictureLink());
            existingCourse.setDescription(course.getDescription());
            existingCourse.setCategory(category);
            existingCourse.setLastUpdatedDate(date);
            return courseRepository.save(existingCourse);
        }
        return null;
    }

    public CourseDetailResponse getCourseDetail(Long id) {
        Course course = courseRepository.findCourseById(id);
        CourseDetailResponse courseDetailResponse = new CourseDetailResponse();
        courseDetailResponse.setName(course.getName());
        courseDetailResponse.setDescription(course.getDescription());
        courseDetailResponse.setPictureLink(course.getPictureLink());
        courseDetailResponse.setPrice(course.getPrice());
        courseDetailResponse.setCategoryId(course.getCategory().getId());
        courseDetailResponse.setCategoryName(course.getCategory().getName());
        return courseDetailResponse;
    }
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
