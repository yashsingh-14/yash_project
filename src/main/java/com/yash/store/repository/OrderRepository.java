package com.yash.store.repository;

import com.yash.store.model.Order;
import com.yash.store.model.User;
import com.yash.store.model.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

    List<Order> findByStatus(OrderStatus status);
}
