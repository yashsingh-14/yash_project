package com.yash.store.repository;

import com.yash.store.model.User;
import com.yash.store.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findByUser(User user);

    void deleteByUserAndProductId(User user, Long productId);
}
