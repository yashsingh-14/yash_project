package com.yash.store.service;

import com.yash.store.model.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {
    private List<Product> wishlistItems = new ArrayList<>();

    public void addToWishlist(Product product) {
        // Check if product already exists to avoid duplicates
        for (Product item : wishlistItems) {
            if (item.getId().equals(product.getId())) {
                return;
            }
        }
        wishlistItems.add(product);
    }

    public void removeFromWishlist(String productId) {
        wishlistItems.removeIf(item -> item.getId().equals(productId));
    }

    public List<Product> getWishlistItems() {
        return wishlistItems;
    }
}
