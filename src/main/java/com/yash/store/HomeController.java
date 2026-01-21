package com.yash.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("popularCollections", productService.getProductsByCategory("popular-collection"));
        model.addAttribute("bestSellers", productService.getProductsByCategory("best-sellers"));
        model.addAttribute("newArrivals", productService.getProductsByCategory("new-arrivals"));
        model.addAttribute("summerEssentials", productService.getProductsByCategory("summer-essentials"));
        model.addAttribute("saleItems", productService.getProductsByCategory("sale"));
        return "index";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
        return "cart";
    }

    @Autowired
    private CartService cartService;

    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable String id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            cartService.addToCart(product);
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable String id) {
        cartService.removeFromCart(id);
        return "redirect:/cart";
    }

    @GetMapping("/cart/increase/{id}")
    public String increaseQuantity(@PathVariable String id) {
        cartService.increaseQuantity(id);
        return "redirect:/cart";
    }

    @GetMapping("/cart/decrease/{id}")
    public String decreaseQuantity(@PathVariable String id) {
        cartService.decreaseQuantity(id);
        return "redirect:/cart";
    }

    @Autowired
    private WishlistService wishlistService;

    @GetMapping("/wishlist")
    public String wishlist(Model model) {
        model.addAttribute("wishlistItems", wishlistService.getWishlistItems());
        return "wishlist";
    }

    @GetMapping("/wishlist/add/{id}")
    public String addToWishlist(@PathVariable String id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            wishlistService.addToWishlist(product);
        }
        return "redirect:/wishlist";
    }

    @GetMapping("/wishlist/remove/{id}")
    public String removeFromWishlist(@PathVariable String id) {
        wishlistService.removeFromWishlist(id);
        return "redirect:/wishlist";
    }

    @GetMapping("/login")
    public String login() {
        return "Login-Signup";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contactpage";
    }
}
