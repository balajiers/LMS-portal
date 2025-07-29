package com.example.lms.service;

import com.example.lms.entity.Course;
import com.example.lms.entity.User;
import java.util.List;
import java.util.Optional;

public interface CourseService {
    Course createCourse(Course course);
    Course updateCourse(Course course);
    void deleteCourse(Long courseId);
    Optional<Course> getCourseById(Long id);
    List<Course> getAllCourses();
    List<Course> getCoursesByInstructor(User instructor);
    void enrollUser(Long courseId, Long userId);
    void unenrollUser(Long courseId, Long userId);
}
