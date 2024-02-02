package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.CreateCourseRequestDTO;
import website.skillforge.be.entities.Course;
import website.skillforge.be.repository.CourseRepository;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    public Course createCourse(CreateCourseRequestDTO course){
        Course newCourse = new Course();
        newCourse.setName(course.getName());
        newCourse.setPrice(course.getPrice());
        newCourse.setTotalOfChapter(course.getTotalOfChapter());
        newCourse.setPictureLink(course.getPictureLink());
        newCourse.setDescription(course.getDescription());
        newCourse.setCreatedDate(course.getCreatedDate());
        newCourse.setStatus(course.getStatus());
        return courseRepository.save(newCourse);
    }
    public int deleteCourseById(Long id){
        courseRepository.deleteById(id);
        return 1;
    }
    public Course updateCourse(Long id, CreateCourseRequestDTO course) {
        Course existingCourse = courseRepository.findById(id).orElse(null);

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

        return null; // hoặc xử lý theo ý bạn khi không tìm thấy khóa học
    }
}
