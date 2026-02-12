package com.yash.store.config;

import com.yash.store.repository.ProductRepository;
import com.yash.store.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

        private final ProductRepository productRepository;
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        public DataSeeder(ProductRepository productRepository, UserRepository userRepository,
                        PasswordEncoder passwordEncoder) {
                this.productRepository = productRepository;
                this.userRepository = userRepository;
                this.passwordEncoder = passwordEncoder;
        }

        @Override
        public void run(String... args) throws Exception {
        }
}
