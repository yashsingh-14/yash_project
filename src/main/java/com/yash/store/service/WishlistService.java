package com.yash.store.service;

import com.yash.store.model.Product;
import com.yash.store.model.User;
import com.yash.store.model.Wishlist;
import com.yash.store.repository.UserRepository;
import com.yash.store.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private UserRepository userRepository;

    public void addToWishlist(Product product) {
        User user = getCurrentUser();

        // Check if already in wishlist
        List<Wishlist> existing = wishlistRepository.findByUser(user);
        for (Wishlist item : existing) {
            if (item.getProduct().getId().equals(product.getId())) {
                return; // Already exists
            }
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setProduct(product);
        wishlistRepository.save(wishlist);
    }

    @Transactional
    public void removeFromWishlist(Long wishlistId) {
        wishlistRepository.deleteById(wishlistId);
    }

    public List<Wishlist> getWishlistItems() {
        User user = getCurrentUser();
        return wishlistRepository.findByUser(user);
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userRepository.findByEmail(email).orElseThrow();
    }
}
