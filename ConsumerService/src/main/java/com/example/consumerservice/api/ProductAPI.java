package com.example.consumerservice.api;

import com.example.consumerservice.domain.Product;
import com.example.consumerservice.dto.PageDTO;
import com.example.consumerservice.dto.ProductDTO;
import com.example.consumerservice.filter.Filters;
import com.example.consumerservice.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductAPI {

    ProductService service;
    Filters filters;

    @GetMapping("/products")
    public PageDTO<Product> findAll(@RequestParam(defaultValue = "0")  Integer offset,
                                    @RequestParam(defaultValue = "5")  Integer size,
                                    @RequestParam(required = false) String keyWord) throws JsonProcessingException {
        return service.findAll(offset, size, keyWord);
    }

    @GetMapping("/products/list")
    public List<Product> findAll(@RequestParam(required = false) Long categoryId,
                                 @RequestParam(required = false) BigDecimal minPrice, @RequestParam(required = false) BigDecimal maxPrice) {
            return filters.checkFiltersParams(categoryId, minPrice, maxPrice);
    }

    @PostMapping("/products")
    public Product create(@RequestBody ProductDTO product) {
        return service.create(product);
    }

    @GetMapping("/products/{id}")
    public Product findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/products/{id}")
    public Product put(@PathVariable Long id, @RequestBody ProductDTO product) {
        return service.put(id, product);
    }

}
