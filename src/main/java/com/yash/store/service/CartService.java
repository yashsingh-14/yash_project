package com.yash.store.service;

import com.yash.store.model.CartItem;
import com.yash.store.model.Product;
import com.yash.store.model.User;
import com.yash.store.repository.CartRepository;
import com.yash.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userRepository.findByEmail(email).orElseThrow();
    }

    public void addToCart(Product product) {
        User user = getCurrentUser();

        CartItem cartItem = cartRepository.findByUserAndProduct(user, product)
                .orElse(new CartItem());

        if (cartItem.getId() == null) {
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }

        cartRepository.save(cartItem);
    }

    public List<CartItem> getCartItems() {
        User user = getCurrentUser();
        return cartRepository.findByUser(user);
    }

    public double getTotalPrice() {
        return getCartItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public void removeFromCart(Long productId) {
        User user = getCurrentUser();
        List<CartItem> cartItems = cartRepository.findByUser(user);
        cartItems.stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresent(cartRepository::delete);
    }

    public void increaseQuantity(Long productId) {
        User user = getCurrentUser();
        List<CartItem> cartItems = cartRepository.findByUser(user);
        cartItems.stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresent(item -> {
                    item.setQuantity(item.getQuantity() + 1);
                    cartRepository.save(item);
                });
    }

    public void decreaseQuantity(Long productId) {
        User user = getCurrentUser();
        List<CartItem> cartItems = cartRepository.findByUser(user);
        cartItems.stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresent(item -> {
                    if (item.getQuantity() > 1) {
                        item.setQuantity(item.getQuantity() - 1);
                        cartRepository.save(item);
                    } else {
                        cartRepository.delete(item);
                    }
                });
    }
}
