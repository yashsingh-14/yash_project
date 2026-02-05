package com.yash.store.controller;

import com.yash.store.model.Product;
import com.yash.store.model.enums.Category;
import com.yash.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CoreController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("popularCollections", productRepository.findByCategory(Category.POPULAR_COLLECTION));
        model.addAttribute("bestSellers", productRepository.findByCategory(Category.BEST_SELLERS));
        model.addAttribute("newArrivals", productRepository.findByCategory(Category.NEW_ARRIVALS));
        model.addAttribute("summerEssentials", productRepository.findByCategory(Category.SUMMER_ESSENTIALS));
        model.addAttribute("saleItems", productRepository.findByCategory(Category.SALE));
        return "index";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contactpage";
    }

    // Moved from old ProductController
    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            model.addAttribute("product", product);
            model.addAttribute("relatedProducts", productRepository.findByCategory(product.getCategory()));
            return "product-detail";
        }
        return "redirect:/";
    }

    @GetMapping("/product/image/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null && product.getImage() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(product.getImage());
        }
        return ResponseEntity.notFound().build();
    }
}
