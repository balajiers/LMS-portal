package com.example.lms.service;

import com.example.lms.entity.Assignment;
import com.example.lms.entity.Course;
import java.util.List;
import java.util.Optional;

public interface AssignmentService {
    Assignment createAssignment(Assignment assignment);
    Assignment updateAssignment(Assignment assignment);
    void deleteAssignment(Long assignmentId);
    Optional<Assignment> getAssignmentById(Long id);
    List<Assignment> getAssignmentsByCourse(Course course);
}
