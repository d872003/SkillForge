package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('TEACHER')")
    public ResponseEntity createCourse(@RequestBody CreateCourseRequestDTO createCourseRequestDTO){
        Course  course = courseService.createCourse(createCourseRequestDTO);
        return ResponseEntity.ok(course);
    }
    @DeleteMapping("/course/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('TEACHER')")
    public ResponseEntity deleteCourse(@PathVariable Long id){
        courseService.deleteCourseById(id);
        return ResponseEntity.ok("Deleted successfully");
    }
    @PutMapping("/course/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('TEACHER')")
    public ResponseEntity updateCourse(@PathVariable Long id, @RequestBody CreateCourseRequestDTO createCourseRequestDTO){
        Course newCourse = courseService.updateCourse(id,createCourseRequestDTO);
        return ResponseEntity.ok(newCourse);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity getCourseById(@PathVariable Long id){
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }
    @GetMapping("/course/{name}")
    public ResponseEntity getCourseByName(@PathVariable String name){
        Course course = courseService.getCourseByName(name);
        return ResponseEntity.ok(course);
    }
    @GetMapping("/course")
    public ResponseEntity getAllCourses(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }
}

