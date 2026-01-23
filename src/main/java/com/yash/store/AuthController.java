package com.yash.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "Login-Signup";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String fullName, @RequestParam String email,
            @RequestParam String password, Model model) {
        User user = userService.registerUser(fullName, email, password);
        if (user != null) {
            model.addAttribute("successMessage", "Registration successful! Please sign in.");
            return "Login-Signup";
        } else {
            model.addAttribute("errorMessage", "Email already registered!");
            return "Login-Signup";
        }
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, HttpSession session,
            Model model) {
        User user = userService.authenticate(email, password);
        if (user != null) {
            session.setAttribute("user", user);
            if ("ADMIN".equals(user.getRole())) {
                return "redirect:/admin/products";
            }
            return "redirect:/";
        } else {
            model.addAttribute("errorMessage", "Invalid email or password!");
            return "Login-Signup";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
