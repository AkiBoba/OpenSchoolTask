package com.example.supplierservice.api.v1;

import com.example.supplierservice.domain.Category;
import com.example.supplierservice.domain.Product;
import com.example.supplierservice.dto.ProductDTO;
import com.example.supplierservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("api/v1/products")
public class ProductController {

    ProductService service;

    @PostMapping
    public Product create(@Valid @RequestBody ProductDTO product) {
        Product newProduct = Product.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(new Category(product.getCategoryId()))
                .build();
        return service.create(newProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page> findPageable(@RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "5") Integer size, @RequestParam(required = false) String keyWord) {
        Pageable pageable = PageRequest.of(offset, size);
        if (keyWord != null && !"".equals(keyWord)) {
            return  new ResponseEntity<>(service.findAll(pageable, keyWord), HttpStatus.OK);
        }
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody ProductDTO product) {
        service.findById(id);
        Product newProduct = Product.builder()
                .id(id)
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(new Category(product.getCategoryId()))
                .build();
        return service.create(newProduct);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
