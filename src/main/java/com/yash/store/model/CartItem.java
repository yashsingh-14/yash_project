package com.yash.store.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Each cart item belongs to one user, but a user can have many cart items.
    @JoinColumn(name = "user_id") // Creates a column user_id in the cart table that stores the foreign key to
                                  // User.id
    private User user; // A Java reference to the User entity.

    @ManyToOne // Each cart item references one product, but a product can appear in many
               // carts.
    @JoinColumn(name = "product_id") // Creates a column product_id in the cart table that stores the foreign key to
                                     // Product.id
    private Product product; // Java reference to the Product entity.

    private Integer quantity = 1;
}
