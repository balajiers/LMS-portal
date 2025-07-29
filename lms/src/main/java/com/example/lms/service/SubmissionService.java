package com.example.lms.service;

import com.example.lms.entity.Submission;
import com.example.lms.entity.Assignment;
import com.example.lms.entity.User;
import java.util.List;
import java.util.Optional;

public interface SubmissionService {
    Submission submitAssignment(Submission submission);
    Submission gradeSubmission(Long submissionId, Double grade);
    List<Submission> getSubmissionsByAssignment(Assignment assignment);
    List<Submission> getSubmissionsByUser(User user);
    Optional<Submission> getSubmissionById(Long id);
}
