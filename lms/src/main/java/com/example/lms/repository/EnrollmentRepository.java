package com.example.lms.repository;

import com.example.lms.entity.Enrollment;
import com.example.lms.entity.User;
import com.example.lms.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByUser(User user);
    List<Enrollment> findByCourse(Course course);

    @Query("SELECT e FROM Enrollment e WHERE e.user.id = :userId")
    List<Enrollment> findAllByUserId(Long userId);
}
