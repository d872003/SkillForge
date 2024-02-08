package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.CreateCourseRequestDTO;
import website.skillforge.be.entities.Account;
import website.skillforge.be.entities.Category;
import website.skillforge.be.entities.Course;
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
    public Course createCourse(CreateCourseRequestDTO course){
        Account account = accountUtil.getCurrentAccount();
        Category category = categoryRepository.findCategoryById(course.getCategoryId());
        Course newCourse = new Course();
        newCourse.setName(course.getName());
        newCourse.setPrice(course.getPrice());
        newCourse.setTotalOfChapter(course.getTotalOfChapter());
        newCourse.setPictureLink(course.getPictureLink());
        newCourse.setDescription(course.getDescription());
        newCourse.setCreatedDate(course.getCreatedDate());
        newCourse.setStatus(course.getStatus());
        newCourse.setCategory(category);
        newCourse.setCreateBy(account);
        return courseRepository.save(newCourse);
    }
    public int deleteCourseById(Long id){
        courseRepository.deleteById(id);
        return 1;
    }
    public Course getCourseById(Long id){
        return courseRepository.findCourseById(id);
    }
    public Course getCourseByName(String name){
        return courseRepository.findCourseByName(name);
    }
    public Course updateCourse(Long id, CreateCourseRequestDTO course) {
        Course existingCourse = courseRepository.findCourseById(id);

        if (existingCourse != null) {
            existingCourse.setName(course.getName());
            existingCourse.setPrice(course.getPrice());
            existingCourse.setTotalOfChapter(course.getTotalOfChapter());
            existingCourse.setPictureLink(course.getPictureLink());
            existingCourse.setDescription(course.getDescription());
            existingCourse.setCreatedDate(course.getCreatedDate());
            existingCourse.setStatus(course.getStatus());

            return courseRepository.save(existingCourse);
        }

        return null; //
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
