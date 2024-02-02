package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.CreateCourseRequestDTO;
import website.skillforge.be.entities.Course;
import website.skillforge.be.services.CourseService;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class CourseController {
    @Autowired
    CourseService courseService;

    @PostMapping("/course")
    public ResponseEntity createCourse(@RequestBody CreateCourseRequestDTO createCourseRequestDTO){
        Course  course = courseService.createCourse(createCourseRequestDTO);
        return ResponseEntity.ok(course);
    }
    @DeleteMapping("/course")
    public ResponseEntity deleteCourse(@Param("Course Id:") Long id){
        courseService.deleteCourseById(id);
        return ResponseEntity.ok("Deleted successfully");
    }
    @PutMapping("/course")
    public ResponseEntity updateCourse(@Param("Course Id:") Long id, @RequestBody CreateCourseRequestDTO createCourseRequestDTO){
        Course newCourse = courseService.updateCourse(id,createCourseRequestDTO);
        return ResponseEntity.ok(newCourse);
    }
}

