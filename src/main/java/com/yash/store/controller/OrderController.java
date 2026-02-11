package com.yash.store.controller;

import com.yash.store.model.Order;
import com.yash.store.model.User;
import com.yash.store.repository.OrderRepository;
import com.yash.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/orders")
    public String viewOrders(Model model) {
        // Get logged-in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        // Get user's orders
        List<Order> orders = orderRepository.findByUser(user);

        model.addAttribute("orders", orders);

        return "orders"; // Thymeleaf template: orders.html
    }

    @PostMapping("/orders/cancel/{id}")
    public String cancelOrder(@PathVariable Long id) {
        Order order = orderRepository.findById(id).orElseThrow();

        // Only allow cancellation if order is PENDING
        if (order.getStatus().name().equals("PENDING")) {
            order.setStatus(com.yash.store.model.enums.OrderStatus.CANCELLED);
            orderRepository.save(order);
        }

        return "redirect:/orders";
    }
}
