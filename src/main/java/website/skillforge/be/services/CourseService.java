package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CreateCourseRequestDTO;
import website.skillforge.be.dto.updateDTO.UpdateCourseDTO;
import website.skillforge.be.entities.Account;
import website.skillforge.be.entities.Category;
import website.skillforge.be.entities.Course;
import website.skillforge.be.enums.status.Status;
import website.skillforge.be.repository.CategoryRepository;
import website.skillforge.be.repository.CourseRepository;
import website.skillforge.be.util.AccountUtil;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AccountUtil accountUtil;

    public Course createCourse(CreateCourseRequestDTO course) {
        Account account = accountUtil.getCurrentAccount();
        Category category = categoryRepository.findCategoryById(course.getCategoryId());
        Course newCourse = new Course();
        newCourse.setName(course.getName());
        newCourse.setPrice(course.getPrice());
        newCourse.setPictureLink(course.getPictureLink());
        newCourse.setDescription(course.getDescription());
        newCourse.setCreatedDate(course.getCreatedDate());
        newCourse.setStatus(Status.DRAFT);
        newCourse.setCategory(category);
        newCourse.setCreateBy(account);
        return courseRepository.save(newCourse);
    }

    public void deleteCourseById(Long id) {
        Course course = courseRepository.findCourseById(id);
        course.setStatus(Status.DELETED);
        courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        return courseRepository.findCourseById(id);
    }

    public Course getCourseByName(String name) {
        return courseRepository.findCourseByName(name);
    }

    public Course updateCourse(Long id, UpdateCourseDTO course) {
        Course existingCourse = courseRepository.findCourseById(id);

        if (existingCourse != null) {
            existingCourse.setName(course.getName());
            existingCourse.setPrice(course.getPrice());
            existingCourse.setPictureLink(course.getPictureLink());
            existingCourse.setDescription(course.getDescription());
            existingCourse.setLastUpdatedDate(course.getLastUpdatedDate());
            existingCourse.setStatus(Status.PUBLISHED);
            return courseRepository.save(existingCourse);
        }

        return null;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
