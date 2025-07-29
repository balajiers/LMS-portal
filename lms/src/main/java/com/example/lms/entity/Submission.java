package com.example.lms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "submissions")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    @Column
    private Double grade;

    public Submission() {}

    public Submission(Long id, User user, Assignment assignment, Double grade) {
        this.id = id;
        this.user = user;
        this.assignment = assignment;
        this.grade = grade;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Assignment getAssignment() { return assignment; }
    public void setAssignment(Assignment assignment) { this.assignment = assignment; }
    public Double getGrade() { return grade; }
    public void setGrade(Double grade) { this.grade = grade; }

    @Override
    public String toString() {
        return "Submission{" +
                "id=" + id +
                ", user=" + (user != null ? user.getUsername() : null) +
                ", assignment=" + (assignment != null ? assignment.getTitle() : null) +
                ", grade=" + grade +
                '}';
    }
}
