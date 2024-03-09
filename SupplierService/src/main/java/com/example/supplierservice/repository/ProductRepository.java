package com.example.supplierservice.repository;

import com.example.supplierservice.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("from Product p join fetch p.category")
    Page<Product> findAll(Pageable pageable);

    @Query("FROM Product p WHERE CONCAT(p.name, p.description) LIKE %?1%")
    Page<Product> findAll(Pageable pageable, String keyWord);

    @Query("from Product p join fetch p.category where p.id = :id")
    Optional<Product> findById(Long id);

}
