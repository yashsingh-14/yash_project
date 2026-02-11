package com.yash.store.config;

import com.yash.store.model.Product;
import com.yash.store.model.User;
import com.yash.store.model.enums.Category;
import com.yash.store.repository.ProductRepository;
import com.yash.store.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

        private final ProductRepository productRepository;
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        public DataSeeder(ProductRepository productRepository, UserRepository userRepository,
                        PasswordEncoder passwordEncoder) {
                this.productRepository = productRepository;
                this.userRepository = userRepository;
                this.passwordEncoder = passwordEncoder;
        }

        @Override
        public void run(String... args) throws Exception {
                // Create admin user if not exists
                if (userRepository.findByEmail("admin@store.com").isEmpty()) {
                        User admin = new User();
                        admin.setFullName("Admin");
                        admin.setEmail("admin@store.com");
                        admin.setPassword(passwordEncoder.encode("admin123"));
                        admin.setRole("ADMIN");
                        userRepository.save(admin);
                }

                if (productRepository.count() == 0) {
                        // Popular Collection
                        productRepository.save(Product.builder()
                                        .name("Classic White Tee")
                                        .description("Premium cotton t-shirt with a comfortable fit.")
                                        .price(499)
                                        .originalPrice(999)
                                        .imageUrl("https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=500")
                                        .category(Category.POPULAR_COLLECTION)
                                        .build());

                        productRepository.save(Product.builder()
                                        .name("Denim Jacket")
                                        .description("Classic blue denim jacket for all seasons.")
                                        .price(1499)
                                        .originalPrice(2999)
                                        .imageUrl("https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=500")
                                        .category(Category.POPULAR_COLLECTION)
                                        .build());

                        // Best Sellers
                        productRepository.save(Product.builder()
                                        .name("Urban Hoodie")
                                        .description("Warm and stylish hoodie for urban lifestyle.")
                                        .price(899)
                                        .originalPrice(1999)
                                        .imageUrl("https://images.unsplash.com/photo-1556821840-3a63f95609a7?w=500")
                                        .category(Category.BEST_SELLERS)
                                        .build());

                        productRepository.save(Product.builder()
                                        .name("Leather Wallet")
                                        .description("Genuine leather wallet with multiple compartments.")
                                        .price(499)
                                        .originalPrice(1299)
                                        .imageUrl("https://images.unsplash.com/photo-1627123424574-1800390cbfae?w=500")
                                        .category(Category.BEST_SELLERS)
                                        .build());

                        // New Arrivals
                        productRepository.save(Product.builder()
                                        .name("Smart Watch")
                                        .description("Feature-rich smartwatch with fitness tracking.")
                                        .price(2499)
                                        .originalPrice(4999)
                                        .imageUrl("https://images.unsplash.com/photo-1579586337278-3befd40fd17a?w=500")
                                        .category(Category.NEW_ARRIVALS)
                                        .build());

                        productRepository.save(Product.builder()
                                        .name("Running Shoes")
                                        .description("Lightweight running shoes for maximum performance.")
                                        .price(1299)
                                        .originalPrice(2499)
                                        .imageUrl("https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=500")
                                        .category(Category.NEW_ARRIVALS)
                                        .build());

                        // Summer Essentials
                        productRepository.save(Product.builder()
                                        .name("Flowey Summer Dress")
                                        .description("Flowey dress perfect for beach vacations")
                                        .price(1199)
                                        .imageUrl("/images/products/trend7.jpg")
                                        .category(Category.SUMMER_ESSENTIALS)
                                        .rating(5.0)
                                        .build());

                        productRepository.save(Product.builder()
                                        .name("Straw Sun Hat")
                                        .description("Essential summer accessory with UV protection")
                                        .price(599)
                                        .imageUrl("/images/products/trend8.jpg")
                                        .category(Category.SUMMER_ESSENTIALS)
                                        .rating(4.0)
                                        .build());

                        productRepository.save(Product.builder()
                                        .name("Linen Shorts")
                                        .description("Comfortable and stylish for hot days")
                                        .price(799)
                                        .imageUrl("/images/products/trend9.jpg")
                                        .category(Category.SUMMER_ESSENTIALS)
                                        .rating(5.0)
                                        .build());

                        // Sale
                        productRepository.save(Product.builder()
                                        .name("Knit Sweater")
                                        .description("Cozy sweater for cool summer evenings")
                                        .price(999)
                                        .originalPrice(1799)
                                        .imageUrl("/images/products/trend10.jpg")
                                        .category(Category.SALE)
                                        .rating(4.0)
                                        .badge("sale")
                                        .build());

                        productRepository.save(Product.builder()
                                        .name("Denim A-Line Skirt")
                                        .description("Classic skirt perfect for pairing with any top")
                                        .price(899)
                                        .originalPrice(1299)
                                        .imageUrl("/images/products/trend11.jpg")
                                        .category(Category.SALE)
                                        .rating(5.0)
                                        .badge("sale")
                                        .build());

                        productRepository.save(Product.builder()
                                        .name("Casual Cotton Blazer")
                                        .description("Lightweight blazer for smart casual looks")
                                        .price(1499)
                                        .originalPrice(2499)
                                        .imageUrl("/images/products/trend12.jpg")
                                        .category(Category.SALE)
                                        .rating(4.0)
                                        .badge("sale")
                                        .build());
                }
        }
}
