package com.example.supplierservice.api.v1;

import com.example.supplierservice.entity.Product;
import com.example.supplierservice.filter.Filters;
import com.example.supplierservice.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductControllerTest {

    private static ProductService service;
    private static Filters filters;
    private static ProductController controller;

    @BeforeAll
    static void setUp() {
        service = Mockito.mock(ProductService.class);
        filters = new Filters(service);
        controller = new ProductController(service, filters);
    }

    @Test
    void findAllReturnListOfProducts() {

        List<Product> products = List.of(Product
                .builder()
                .id(1L)
                .name("яблоки")
                .description("зеленые")
                .price(new BigDecimal("10.20"))
                .build(),
        Product
                .builder()
                .id(2L)
                .name("груши")
                .description("сезонные")
                .price(new BigDecimal("15.10"))
                .build()
        );

        Mockito.doReturn(products).when(service).findAll();

        ResponseEntity<List<Product>> responseEntity = controller.findAll();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(products, responseEntity.getBody());
    }

    @Test
    void create() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findPageable() {
    }

    @Test
    void findFiltered() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}