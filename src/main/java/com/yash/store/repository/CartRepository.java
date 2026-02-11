package com.yash.store.repository;

import com.yash.store.model.CartItem;
import com.yash.store.model.Product;
import com.yash.store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);

    Optional<CartItem> findByUserAndProduct(User user, Product product);
}
