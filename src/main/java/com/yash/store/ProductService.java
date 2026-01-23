package com.yash.store;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void init() {
        if (productRepository.count() == 0) {
            // Popular Collections
            productRepository
                    .save(new Product("p1", "Mumbai Styles", "Trendy urban fashion inspired by the city of dreams",
                            "₹500", null, "/images/products/popular1.jpg", "popular-collection", 4.0, null));
            productRepository.save(new Product("p2", "Delhi Trends", "Traditional elegance meets contemporary fashion",
                    "₹800", null, "/images/products/popular2.jpg", "popular-collection", 5.0, null));
            productRepository
                    .save(new Product("p3", "Bangalore Fits", "Casual comfort for the tech-savvy urban lifestyle",
                            "₹600", null, "/images/products/popular3.jpg", "popular-collection", 3.0, null));

            // Best Sellers
            productRepository
                    .save(new Product("t1", "Classic Denim Jacket", "Versatile jacket perfect for any casual occasion",
                            "₹1,299", "₹1,799", "/images/products/trend1.jpg", "best-sellers", 5.0, "popular"));
            productRepository.save(
                    new Product("t2", "Floral Print Dress", "Lightweight summer dress with vibrant floral pattern",
                            "₹899", null, "/images/products/trend2.jpg", "best-sellers", 4.0, "popular"));
            productRepository.save(new Product("t3", "Premium Linen Shirt", "Breathable fabric perfect for hot weather",
                    "₹1,099", "₹1,499", "/images/products/trend3.jpg", "best-sellers", 5.0, "popular"));

            // New Arrivals
            productRepository.save(new Product("t4", "Crochet Summer Top", "Handmade crochet top with delicate details",
                    "₹1,499", null, "/images/products/trend4.jpg", "new-arrivals", 4.0, "new"));
            productRepository
                    .save(new Product("t5", "Wide Leg Linen Pants", "Comfortable and stylish for summer outings",
                            "₹1,299", null, "/images/products/trend5.jpg", "new-arrivals", 5.0, "new"));
            productRepository.save(new Product("t6", "Oversized Cotton Shirt", "Relaxed fit shirt perfect for layering",
                    "₹999", null, "/images/products/trend6.jpg", "new-arrivals", 3.0, "new"));

            // Summer Essentials
            productRepository
                    .save(new Product("t7", "Lightweight Maxi Dress", "Flowey dress perfect for beach vacations",
                            "₹1,199", null, "/images/products/trend7.jpg", "summer-essentials", 5.0, null));
            productRepository.save(new Product("t8", "Straw Sun Hat", "Essential summer accessory with UV protection",
                    "₹599", null, "/images/products/trend8.jpg", "summer-essentials", 4.0, null));
            productRepository.save(new Product("t9", "Linen Shorts", "Comfortable and stylish for hot days", "₹799",
                    null, "/images/products/trend9.jpg", "summer-essentials", 5.0, null));

            // Sale
            productRepository.save(new Product("t10", "Knit Sweater", "Cozy sweater for cool summer evenings", "₹999",
                    "₹1,799", "/images/products/trend10.jpg", "sale", 4.0, "sale"));
            productRepository
                    .save(new Product("t11", "Denim A-Line Skirt", "Classic skirt perfect for pairing with any top",
                            "₹899", "₹1,299", "/images/products/trend11.jpg", "sale", 5.0, "sale"));
            productRepository
                    .save(new Product("t12", "Casual Cotton Blazer", "Lightweight blazer for smart casual looks",
                            "₹1,499", "₹2,499", "/images/products/trend12.jpg", "sale", 4.0, "sale"));
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    // CRUD Methods
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
