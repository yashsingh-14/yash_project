package com.yash.store.controller;

import com.yash.store.model.Address;
import com.yash.store.model.CartItem;
import com.yash.store.model.Order;
import com.yash.store.model.User;
import com.yash.store.repository.AddressRepository;
import com.yash.store.repository.CartRepository;
import com.yash.store.repository.OrderRepository;
import com.yash.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/payment")
    public String showPaymentPage(@RequestParam Long addressId, Model model) {
        // Get logged-in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        // Get cart items
        List<CartItem> cartItems = cartRepository.findByUser(user);

        // Calculate total
        int total = 0;
        for (CartItem item : cartItems) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }

        model.addAttribute("addressId", addressId);
        model.addAttribute("itemCount", cartItems.size());
        model.addAttribute("totalPrice", total);

        return "payment";
    }

    @PostMapping("/payment/process")
    public String processPayment(@RequestParam Long addressId,
            @RequestParam String paymentMethod) {
        // 1. Get logged-in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        // 2. Get cart items
        List<CartItem> cartItems = cartRepository.findByUser(user);

        // 3. Get selected address
        Address address = addressRepository.findById(addressId).orElseThrow();

        // 4. Create orders from cart items
        for (CartItem item : cartItems) {
            Order order = new Order();
            order.setUser(user);
            order.setProduct(item.getProduct());
            order.setQuantity(item.getQuantity());
            order.setAddress(address);
            order.setPaymentMethod(paymentMethod);
            // orderAt and status are set in Order constructor

            orderRepository.save(order);
        }

        // 5. Clear cart after order placement
        cartRepository.deleteAll(cartItems);

        return "redirect:/orders";
    }
}
