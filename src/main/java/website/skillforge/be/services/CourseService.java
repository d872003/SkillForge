package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CourseDetailResponse;
import website.skillforge.be.dto.createDTO.CreateCourseRequestDTO;
import website.skillforge.be.dto.createDTO.GetAllLessonResponse;
import website.skillforge.be.dto.createDTO.quizDto.GetAllQuizResponse;
import website.skillforge.be.entities.*;
import website.skillforge.be.entities.quiz.Quiz;
import website.skillforge.be.enums.Role;
import website.skillforge.be.enums.status.CourseStatus;
import website.skillforge.be.repository.CategoryRepository;
import website.skillforge.be.repository.CourseEnrollmentRepository;
import website.skillforge.be.repository.CourseRepository;

import website.skillforge.be.services.quiz.QuizService;
import website.skillforge.be.util.AccountUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
    ProgressService progressService;
    @Autowired
    CourseEnrollmentRepository courseEnrollmentRepository;
    @Autowired
    AccountUtil accountUtil;

    public Course createCourse(CreateCourseRequestDTO course) {
        Account account = accountUtil.getCurrentAccount();
        Date date = new Date();
        Category category = categoryRepository.findCategoryById(course.getCategoryId());
        Course newCourse = new Course();
        newCourse.setName(course.getName());
        newCourse.setPrice(course.getPrice());
        newCourse.setCode(course.getCode());
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

    public Course getCourseByCode(String code) {
        return courseRepository.findCourseByCode(code);
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
            existingCourse.setCode(course.getCode());
            existingCourse.setPrice(course.getPrice());
            existingCourse.setPictureLink(course.getPictureLink());
            existingCourse.setDescription(course.getDescription());
            existingCourse.setCategory(category);
            existingCourse.setLastUpdatedDate(date);
            return courseRepository.save(existingCourse);
        }
        return null;
    }

    public List<CourseDetailResponse> getCourseByTeacherId() {
        Account account = accountUtil.getCurrentAccount();
        List<CourseDetailResponse> courseDetailResponse = new ArrayList<>();
        if (account.getRole() == Role.TEACHER) {
            List<Course> courses = courseRepository.findCourseByCreateById(account.getId());
            for (Course course : courses) {
                courseDetailResponse.add(getEnrollCourseDetail(course.getId()));
            }
        }
        return courseDetailResponse;
    }

    public List<CourseEnrollment> getStudentEnrollCourseDetail(long id) {
        return courseEnrollmentRepository.findCourseEnrollmentByCourse_id(id);
    }

    public List<CourseDetailResponse> getCourseDetail() {
        Account account = null;
        try {
            account = accountUtil.getCurrentAccount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<CourseDetailResponse> courseDetailResponse = new ArrayList<>();
        List<Course> courses = getAllCourses();
        List<CourseEnrollment> courseEnrollment = new ArrayList<>();
        if (account != null) {
            courseEnrollment = courseEnrollmentRepository.findCourseEnrollmentByAccount_id(account.getId());
        }
        for (Course course : courses) {
            boolean isEnrolled = false;
            for (CourseEnrollment enrollment : courseEnrollment) {
                if (course.getId().equals(enrollment.getCourse().getId())) {
                    isEnrolled = true;
                    break;
                }
            }
            if (!isEnrolled) {
                courseDetailResponse.add(getCourseDetail(course.getId()));
            }
        }
        return courseDetailResponse;
    }

    public List<CourseDetailResponse> getEnrollCourseDetail() {
        Account account = accountUtil.getCurrentAccount();
        List<CourseEnrollment> courseEnrollment = new ArrayList<>();
        List<CourseDetailResponse> courseDetailResponse = new ArrayList<>();
        if (account != null) {
            courseEnrollment = courseEnrollmentRepository.findCourseEnrollmentByAccount_id(account.getId());
            for (CourseEnrollment enrollment : courseEnrollment) {
                courseDetailResponse.add(getEnrollCourseDetail(enrollment.getCourse().getId()));
            }
        }
        return courseDetailResponse;
    }
    public CourseDetailResponse getCourseDetail(Long id) {
        CourseDetailResponse courseDetailResponse = new CourseDetailResponse();
        Course course = getCourseById(id);
        courseDetailResponse.setId(course.getId());
        courseDetailResponse.setName(course.getName());
        courseDetailResponse.setCode(course.getCode());
        courseDetailResponse.setPrice(course.getPrice());
        courseDetailResponse.setPictureLink(course.getPictureLink());
        courseDetailResponse.setDescription(course.getDescription());
        courseDetailResponse.setCategory(course.getCategory());
        courseDetailResponse.setCreateBy(course.getCreateBy());
        List<Chapter> chapters = chapterService.GetChaptersByCourseId(course.getId());
        courseDetailResponse.setChapters(chapters);
        List<GetAllLessonResponse> allLessons = new ArrayList<>();
        for (Chapter chapter : chapters) {
            List<GetAllLessonResponse> lessons = lessonService.getAllLessonDTOByChapterId(chapter.getId());
            allLessons.addAll(lessons);
        }
        courseDetailResponse.setLessons(allLessons);
        return courseDetailResponse;
    }
    //

    public CourseDetailResponse getEnrollCourseDetail(Long id) {
        CourseDetailResponse courseDetailResponse = new CourseDetailResponse();
        Course course = getCourseById(id);
        courseDetailResponse.setId(course.getId());
        courseDetailResponse.setName(course.getName());
        courseDetailResponse.setCode(course.getCode());
        courseDetailResponse.setPrice(course.getPrice());
        courseDetailResponse.setPictureLink(course.getPictureLink());
        courseDetailResponse.setDescription(course.getDescription());
        courseDetailResponse.setCategory(course.getCategory());
        courseDetailResponse.setCreateBy(course.getCreateBy());
        List<Chapter> chapters = chapterService.GetChaptersByCourseId(course.getId());
        courseDetailResponse.setChapters(chapters);
        List<GetAllLessonResponse> allLessons = new ArrayList<>();
        for (Chapter chapter : chapters) {
            List<GetAllLessonResponse> lessons = lessonService.getAllLessonDTOByChapterId(chapter.getId());
            for (GetAllLessonResponse lesson : lessons) {
                Progress progress = progressService.getProgressByLessonIdAndAccountId(lesson.getId());
                if (progress != null) {
                    lesson.SetIsCompleted(true);
                }
                allLessons.add(lesson);
            }
        }
        for (GetAllLessonResponse lesson : allLessons) {
            Quiz quiz = quizService.getQuizByLessonId2(lesson.getId());
            if (quiz != null) {
                lesson.setQuiz(quiz);
            }
        }
        courseDetailResponse.setLessons(allLessons);
        return courseDetailResponse;
    }


    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
