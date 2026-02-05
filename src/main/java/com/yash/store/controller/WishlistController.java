package com.yash.store.controller;

import com.yash.store.model.Product;
import com.yash.store.repository.ProductRepository;
import com.yash.store.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String viewWishlist(Model model) {
        model.addAttribute("wishlistItems", wishlistService.getWishlistItems());
        return "wishlist";
    }

    @GetMapping("/add/{id}")
    public String addToWishlist(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            wishlistService.addToWishlist(product);
        }
        return "redirect:/wishlist";
    }

    @GetMapping("/remove/{id}")
    public String removeFromWishlist(@PathVariable Long id) {
        wishlistService.removeFromWishlist(id);
        return "redirect:/wishlist";
    }
}
