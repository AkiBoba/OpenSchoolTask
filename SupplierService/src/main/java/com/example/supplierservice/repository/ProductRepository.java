package com.example.supplierservice.repository;

import com.example.supplierservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("FROM Product p JOIN FETCH p.category")
    List<Product> findAll();

    @Query("FROM Product p JOIN FETCH p.category WHERE p.price > :minPrice")
    List<Product> findAll(BigDecimal minPrice);

    @Query("FROM Product p JOIN FETCH p.category")
    Page<Product> findAll(Pageable pageable);

    @Query("FROM Product p WHERE CONCAT(p.name, p.description) LIKE %?1%")
    Page<Product> findAll(Pageable pageable, String keyWord);

    @Query("FROM Product p JOIN FETCH p.category WHERE p.id = :id")
    Optional<Product> findById(Long id);

    @Query("FROM Product p JOIN FETCH p.category WHERE p.category.id = :categoryId AND p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> findAll(Long categoryId, BigDecimal minPrice, BigDecimal maxPrice);

    @Query("FROM Product p JOIN FETCH p.category WHERE p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> findAll(BigDecimal minPrice, BigDecimal maxPrice);

    @Query("FROM Product p JOIN FETCH p.category WHERE p.category.id = :categoryId AND p.price > :minPrice")
    List<Product> findAll(Long categoryId, BigDecimal minPrice);
}
