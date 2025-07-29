package com.example.lms.controller;

import com.example.lms.entity.Submission;
import com.example.lms.entity.Assignment;
import com.example.lms.entity.User;
import com.example.lms.service.SubmissionService;
import com.example.lms.service.FileStorageService;
import com.example.lms.util.FileTypeValidator;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/submissions")

public class SubmissionController {
    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private FileStorageService fileStorageService;


    // File upload endpoint for assignment submissions
    @PostMapping("/upload/{assignmentId}")
    public ResponseEntity<?> uploadSubmission(@PathVariable Long assignmentId,
                                              @RequestParam("file") MultipartFile file,
                                              @RequestParam("userId") Long userId) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }
        if (!FileTypeValidator.isValid(file)) {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Invalid file type. Only PDF and Word documents are allowed.");
        }
        String subDir = "assignment-" + assignmentId + "/user-" + userId;
        String fileName = fileStorageService.storeFile(file, subDir);
        // Optionally, link fileName to Submission entity here
        return ResponseEntity.ok().body("File uploaded successfully: " + fileName);
    }
    // File download endpoint with security checks (e.g., only owner or instructor can download)
    @GetMapping("/download/{assignmentId}/{userId}/{fileName}")
    public ResponseEntity<Resource> downloadSubmission(@PathVariable Long assignmentId,
                                                      @PathVariable Long userId,
                                                      @PathVariable String fileName) {
        // TODO: Add security checks for user roles/ownership
        String subDir = "assignment-" + assignmentId + "/user-" + userId;
        Resource resource = fileStorageService.loadFileAsResource(fileName, subDir);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @PutMapping("/{id}/grade")
    public ResponseEntity<Submission> gradeSubmission(@PathVariable Long id, @RequestParam Double grade) {
        return ResponseEntity.ok(submissionService.gradeSubmission(id, grade));
    }

    @GetMapping("/assignment/{assignmentId}")
    public List<Submission> getSubmissionsByAssignment(@PathVariable Long assignmentId) {
        Assignment assignment = new Assignment();
        assignment.setId(assignmentId);
        return submissionService.getSubmissionsByAssignment(assignment);
    }

    @GetMapping("/user/{userId}")
    public List<Submission> getSubmissionsByUser(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        return submissionService.getSubmissionsByUser(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Submission> getSubmissionById(@PathVariable Long id) {
        Optional<Submission> submission = submissionService.getSubmissionById(id);
        return submission.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
