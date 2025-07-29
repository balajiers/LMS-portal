package com.example.lms.repository;

import com.example.lms.entity.Submission;
import com.example.lms.entity.Assignment;
import com.example.lms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByAssignment(Assignment assignment);
    List<Submission> findByUser(User user);
    List<Submission> findByAssignmentAndUser(Assignment assignment, User user);

    @Query("SELECT s FROM Submission s WHERE s.grade IS NOT NULL")
    List<Submission> findAllGraded();
}
