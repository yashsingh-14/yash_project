package com.yash.store.service;

import com.yash.store.model.User;
import com.yash.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String fullName, String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            return null; // Email already exists
        }

        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password)); // Hash password with BCrypt
        user.setRole("USER");

        return userRepository.save(user);
    }

    // Authentication is now handled by Spring Security, so we remove the
    // authenticate method
}
