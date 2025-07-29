package com.example.lms.controller;

import com.example.lms.entity.Course;
import com.example.lms.entity.User;
import com.example.lms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        course.setId(id);
        return ResponseEntity.ok(courseService.updateCourse(course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted");
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{courseId}/enroll/{userId}")
    public ResponseEntity<?> enrollUser(@PathVariable Long courseId, @PathVariable Long userId) {
        courseService.enrollUser(courseId, userId);
        return ResponseEntity.ok("User enrolled");
    }

    @PostMapping("/{courseId}/unenroll/{userId}")
    public ResponseEntity<?> unenrollUser(@PathVariable Long courseId, @PathVariable Long userId) {
        courseService.unenrollUser(courseId, userId);
        return ResponseEntity.ok("User unenrolled");
    }
}
