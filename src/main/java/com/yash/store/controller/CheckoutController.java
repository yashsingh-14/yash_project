package com.yash.store.controller;

import com.yash.store.model.Address;
import com.yash.store.model.CartItem;
import com.yash.store.model.User;
import com.yash.store.repository.AddressRepository;
import com.yash.store.repository.CartRepository;
import com.yash.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CheckoutController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/checkout")
    public String checkout(Model model) {
        // 1. Get logged-in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        // 2. Get cart items for user
        List<CartItem> cartItems = cartRepository.findByUser(user);

        // 3. Calculate subtotal
        int total = 0;
        for (CartItem item : cartItems) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }

        // 4. Get user addresses
        List<Address> addresses = addressRepository.findAllByUser(user);

        // 5. Add attributes to model
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);
        model.addAttribute("shipping", 50); // fixed shipping
        model.addAttribute("finalPrice", total + 50);
        model.addAttribute("addresses", addresses);
        model.addAttribute("currentUser", user);

        return "checkout"; // Thymeleaf template: checkout.html
    }
}
