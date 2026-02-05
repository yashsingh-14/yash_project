package com.yash.store.controller;

import com.yash.store.model.Product;
import com.yash.store.model.enums.Category;
import com.yash.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductRepository productRepository;

    // 1. List All Products
    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "admin/product-list";
    }

    // 2. Add Product Form
    @GetMapping("/products/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", Category.values());
        return "admin/product-form";
    }

    // 3. Save Product (Create or Update)
    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute("product") Product product,
            @RequestParam("imageFile") MultipartFile imageFile) throws IOException {

        // Handle Image Upload
        if (!imageFile.isEmpty()) {
            product.setImage(imageFile.getBytes());
        } else if (product.getId() != null) {
            // Keep existing image if editing and no new file selected
            Product existingProduct = productRepository.findById(product.getId()).orElse(null);
            if (existingProduct != null && existingProduct.getImage() != null) {
                product.setImage(existingProduct.getImage());
            }
        }

        productRepository.save(product);
        return "redirect:/admin/products";
    }

    // 4. Edit Product Form
    @GetMapping("/products/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            model.addAttribute("product", product);
            model.addAttribute("categories", Category.values());
            return "admin/product-form";
        }
        return "redirect:/admin/products";
    }

    // 5. Delete Product
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/admin/products";
    }
}
