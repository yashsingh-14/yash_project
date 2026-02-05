package com.yash.store.service;

import com.yash.store.model.CartItem;
import com.yash.store.model.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private List<CartItem> cartItems = new ArrayList<>();

    public void addToCart(Product product) {
        // Check if product already exists
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        cartItems.add(new CartItem(product, 1));
    }

    public void increaseQuantity(Long productId) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
    }

    public void decreaseQuantity(Long productId) {
        CartItem itemToRemove = null;
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(productId)) {
                if (item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);
                } else {
                    itemToRemove = item;
                }
                break;
            }
        }
        if (itemToRemove != null) {
            cartItems.remove(itemToRemove);
        }
    }

    public void removeFromCart(Long productId) {
        cartItems.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : cartItems) {
            if (item.getProduct().getPrice() != null) {
                total += item.getProduct().getPrice() * item.getQuantity();
            }
        }
        return total;
    }
}
