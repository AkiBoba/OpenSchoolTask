package com.example.supplierservice.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonSerialize(as = Category.class)
    private Category category;

//    @Transient
    @OneToMany(cascade = CascadeType.ALL)
    private List<Rating> ratings;

//    @Transient
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Review> reviews;

    public Product(Long id) {
        this.id = id;
    }
}
