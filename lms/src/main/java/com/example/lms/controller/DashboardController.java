package com.example.lms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    @GetMapping
    public ResponseEntity<?> getDashboard(Authentication authentication) {
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        // Return different dashboard data based on role
        return ResponseEntity.ok("Dashboard for role: " + role);
    }
}
