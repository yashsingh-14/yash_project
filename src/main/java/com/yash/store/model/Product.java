package com.yash.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private String id;
    private String name;

    @Column(length = 1000)
    private String description;

    private String price;
    private String originalPrice;
    private String imageUrl;
    private String category; // "popular-collection", "best-sellers", "new-arrivals", "sale",
                             // "summer-essentials"
    private double rating;
    private String badge; // "sale", "new", "popular", null
}

