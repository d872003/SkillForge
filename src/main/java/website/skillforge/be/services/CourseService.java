package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CourseDetailResponse;
import website.skillforge.be.dto.createDTO.EnrolledCourseDetailResponse;
import website.skillforge.be.dto.createDTO.CreateCourseRequestDTO;
import website.skillforge.be.dto.createDTO.GetAllLessonResponse;
import website.skillforge.be.entities.accounts.Account;
import website.skillforge.be.entities.courses.*;
import website.skillforge.be.entities.quizzes.Quiz;
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

    public List<EnrolledCourseDetailResponse> getCourseByTeacherId() {
        Account account = accountUtil.getCurrentAccount();
        List<EnrolledCourseDetailResponse> enrolledCourseDetailResponse = new ArrayList<>();
        if (account.getRole() == Role.TEACHER) {
            List<Course> courses = courseRepository.findCourseByCreateById(account.getId());
            for (Course course : courses) {
                enrolledCourseDetailResponse.add(getEnrollCourseDetail(course.getId()));
            }
        }
        return enrolledCourseDetailResponse;
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
        List<CourseDetailResponse> enrolledCourseDetailResponse = new ArrayList<>();
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
                enrolledCourseDetailResponse.add(getCourseDetail(course.getId()));
            }
        }
        return enrolledCourseDetailResponse;
    }

    public List<EnrolledCourseDetailResponse> getEnrollCourseDetail() {
        Account account = accountUtil.getCurrentAccount();
        List<CourseEnrollment> courseEnrollment = new ArrayList<>();
        List<EnrolledCourseDetailResponse> enrolledCourseDetailResponse = new ArrayList<>();
        if (account != null) {
            courseEnrollment = courseEnrollmentRepository.findCourseEnrollmentByAccount_id(account.getId());
            for (CourseEnrollment enrollment : courseEnrollment) {
                enrolledCourseDetailResponse.add(getEnrollCourseDetail(enrollment.getCourse().getId()));
            }
        }
        return enrolledCourseDetailResponse;
    }
    public CourseDetailResponse getCourseDetail(Long id) {
        CourseDetailResponse enrolledCourseDetailResponse = new CourseDetailResponse();
        Course course = getCourseById(id);
        enrolledCourseDetailResponse.setId(course.getId());
        enrolledCourseDetailResponse.setName(course.getName());
        enrolledCourseDetailResponse.setCode(course.getCode());
        enrolledCourseDetailResponse.setPrice(course.getPrice());
        enrolledCourseDetailResponse.setPictureLink(course.getPictureLink());
        enrolledCourseDetailResponse.setDescription(course.getDescription());
        enrolledCourseDetailResponse.setCategory(course.getCategory());
        enrolledCourseDetailResponse.setCreateBy(course.getCreateBy());
        List<Chapter> chapters = chapterService.GetChaptersByCourseId(course.getId());
        enrolledCourseDetailResponse.setChapters(chapters);
        for (Chapter chapter : chapters) {
            chapter.setLesson(lessonService.getAllLessonByChapterId(chapter.getId()));
        }
        return enrolledCourseDetailResponse;
    }
    //

    public EnrolledCourseDetailResponse getEnrollCourseDetail(Long id) {
        EnrolledCourseDetailResponse enrolledCourseDetailResponse = new EnrolledCourseDetailResponse();
        Course course = getCourseById(id);
        enrolledCourseDetailResponse.setId(course.getId());
        enrolledCourseDetailResponse.setName(course.getName());
        enrolledCourseDetailResponse.setCode(course.getCode());
        enrolledCourseDetailResponse.setPrice(course.getPrice());
        enrolledCourseDetailResponse.setPictureLink(course.getPictureLink());
        enrolledCourseDetailResponse.setDescription(course.getDescription());
        enrolledCourseDetailResponse.setCategory(course.getCategory());
        enrolledCourseDetailResponse.setCreateBy(course.getCreateBy());
        List<Chapter> chapters = chapterService.GetChaptersByCourseId(course.getId());
        enrolledCourseDetailResponse.setChapters(chapters);
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
        if (progressService.percentCourse(id) == 100) {
            enrolledCourseDetailResponse.setFineshed(true);
        }
        ;
        enrolledCourseDetailResponse.setLessons(allLessons);
        return enrolledCourseDetailResponse;
    }

    public List<Course> getCourseByContainsName(String name) {
        List<Course> courses = courseRepository.findAll();
        List<Course> result = new ArrayList<>();
        for (Course course : courses) {
            if (course.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(course);
            }
        }
        return result;
    }
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
