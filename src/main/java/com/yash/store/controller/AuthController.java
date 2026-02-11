package com.yash.store.controller;

import com.yash.store.model.User;
import com.yash.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginPage() {
        return "Login-Signup";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String password,
            Model model) {

        // Check if user already exists
        if (userRepository.findByEmail(email).isPresent()) {
            model.addAttribute("errorMessage", "Email already registered!");
            return "Login-Signup";
        }

        // Create a new user with default role USER
        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");

        // Save to MySQL
        userRepository.save(user);

        // Redirect to login page after registration
        model.addAttribute("successMessage", "Registration successful! Please sign in.");
        return "Login-Signup";
    }

    // Login is handled by Spring Security automatically
    // Logout is handled by Spring Security automatically
}
