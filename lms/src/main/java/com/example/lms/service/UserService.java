package com.example.lms.service;

import com.example.lms.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User registerUser(User user);
    Optional<User> authenticate(String username, String password);
    void changeUserRole(Long userId, String newRole);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
}
