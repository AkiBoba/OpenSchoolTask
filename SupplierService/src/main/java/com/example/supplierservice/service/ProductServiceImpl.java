package com.example.supplierservice.service;

import com.example.supplierservice.entity.Product;
import com.example.supplierservice.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {

    ProductRepository repository;

    @Override
    public Product create(Product product) {
        return repository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Product> findAll(Pageable pageable, String keyWord) {
        return repository.findAll(pageable, keyWord);
    }

    @Override
    public List<Product> findAll(Long categoryId, BigDecimal minPrice, BigDecimal maxPrice) {
        return repository.findAll(categoryId, minPrice, maxPrice);
    }

    @Override
    public List<Product> findAll(BigDecimal minPrice, BigDecimal maxPrice) {
        return repository.findAll(minPrice, maxPrice);
    }

    @Override
    public List<Product> findAll(Long categoryId, BigDecimal minPrice) {
        return repository.findAll(categoryId, minPrice);
    }

    @Override
    public List<Product> findAll(BigDecimal minPrice) {
        return repository.findAll(minPrice);
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        repository.delete(new Product(id));
    }
}
