package com.yash.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(String fullName, String email, String password) {
        if (userRepository.findByEmail(email) != null) {
            return null; // Email already exists
        }
        User newUser = new User(fullName, email, password, "USER");
        return userRepository.save(newUser);
    }

    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
