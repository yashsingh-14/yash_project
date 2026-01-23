package com.yash.store.controller;

import com.yash.store.model.Product;
import com.yash.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    // 1. List All Products
    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin/product-list";
    }

    // 2. Add Product Form
    @GetMapping("/products/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin/product-form";
    }

    // 3. Save Product (Create or Update)
    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        // Generate ID if missing (New Product)
        if (product.getId() == null || product.getId().isEmpty()) {
            product.setId(UUID.randomUUID().toString());
        }
        productService.saveProduct(product);
        return "redirect:/admin/products";
    }

    // 4. Edit Product Form
    @GetMapping("/products/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Product product = productService.getProductById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "admin/product-form";
        }
        return "redirect:/admin/products";
    }

    // 5. Delete Product
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}
