package com.example.supplierservice.api.v1;

import com.example.supplierservice.entity.Category;
import com.example.supplierservice.entity.Product;
import com.example.supplierservice.dto.ProductDTO;
import com.example.supplierservice.filter.Filters;
import com.example.supplierservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
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

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("api/v1/products")
public class ProductController {

    ProductService service;
    Filters filters;

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody ProductDTO product) {
        Product newProduct = Product.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(new Category(product.getCategoryId()))
                .build();
        return new ResponseEntity<>(service.create(newProduct), HttpStatus.OK);
    }

    @Operation(description = "Контроллер принимает запрос на получение списка всех продуктов")
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @Operation(description = "Контроллер принимает запрос на получение списка всех продуктов с постраничным выводом")
    @GetMapping("/pageable")
    public ResponseEntity<Page> findPageable(@RequestParam(defaultValue = "0") Integer offset,
                                             @RequestParam(defaultValue = "5") Integer size,
                                             @RequestParam(required = false) String keyWord) {
        Pageable pageable = PageRequest.of(offset, size);
        if (keyWord != null && !"".equals(keyWord)) {
            return  new ResponseEntity<>(service.findAll(pageable, keyWord), HttpStatus.OK);
        }
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }

    @Operation(description = "Контроллер принимает запрос на получение списка всех продуктов, допустимы параметры для фильтрации результата")
    @GetMapping("/filtered")
    public ResponseEntity<List<Product>> findFiltered(@RequestParam(required = false) Long categoryId,
                                                      @RequestParam(defaultValue = "0") BigDecimal minPrice,
                                                      @RequestParam(required = false) BigDecimal maxPrice) {
        return new ResponseEntity<>(filters.checkFiltersParams(categoryId, minPrice, maxPrice), HttpStatus.OK);
    }

    @Operation(description = "Контроллер принимает запрос на получение продукта по идентификатору")
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(description = "Контроллер принимает запрос на изменение продукта по идентификатору")
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody ProductDTO product) {
        service.findById(id);
        Product newProduct = Product.builder()
                .id(id)
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(new Category(product.getCategoryId()))
                .build();
        return new ResponseEntity<>(service.create(newProduct), HttpStatus.OK);
    }

    @Operation(description = "Контроллер принимает запрос на удаление продукта по идентификатору")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
