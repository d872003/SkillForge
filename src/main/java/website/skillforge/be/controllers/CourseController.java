package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.CourseDetailResponse;
import website.skillforge.be.dto.createDTO.CreateCourseRequestDTO;
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
    public ResponseEntity createCourse(@RequestBody CreateCourseRequestDTO createCourseRequestDTO) {
        Course course = courseService.createCourse(createCourseRequestDTO);
        return ResponseEntity.ok(course);
    }
    @DeleteMapping("/course/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('TEACHER')")
    public ResponseEntity deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @PutMapping("/course/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('TEACHER')")
    public ResponseEntity updateCourse(@PathVariable Long id, @RequestBody CreateCourseRequestDTO updateCourseDTO) {
        Course newCourse = courseService.updateCourse(id, updateCourseDTO);
        return ResponseEntity.ok(newCourse);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/courseByName/{name}")
    public ResponseEntity getCourseByName(@PathVariable String name) {
        Course course = courseService.getCourseByName(name);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/course/{id}/detail")
    public ResponseEntity getCourseDetail(@PathVariable Long id) {
        CourseDetailResponse courseDetailResponse = courseService.getCourseDetail(id);
        return ResponseEntity.ok(courseDetailResponse);
    }

    @GetMapping("/course")
    public ResponseEntity getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }
}

