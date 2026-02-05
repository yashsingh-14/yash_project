package com.yash.store.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Lob;
import com.yash.store.model.enums.Category;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String smallDescription;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String description;

    private Integer price; // Selling Price
    private Integer originalPrice; // MRP / Discounted From

    private String imageUrl; // For URL based images

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image; // For Uploaded images

    private double rating;
    private String badge; // "sale", "new", "popular", null
}
