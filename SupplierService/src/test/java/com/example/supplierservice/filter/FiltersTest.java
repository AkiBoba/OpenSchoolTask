package com.example.supplierservice.filter;

import com.example.supplierservice.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

class FiltersTest {

    private static Filters filters;
    private static ProductService service;

    @BeforeAll
    static void setUp() {
        service = Mockito.mock(ProductService.class);
        filters = new Filters(service);
    }

    @Test
    void checkFiltersParamsWhenOnlyMinPriceCallFindAllMinPrice() {

        Long categoryId = null;
        BigDecimal minPrice = BigDecimal.valueOf(10);
        BigDecimal maxPrice = null;

        filters.checkFiltersParams(categoryId, minPrice, maxPrice);
        Mockito.verify(service).findAll(minPrice);

    }

    @Test
    void checkFiltersParamsWhenTwoPriceCallFindAllTwoPrice() {

        Long categoryId = null;
        BigDecimal minPrice = BigDecimal.valueOf(10);
        BigDecimal maxPrice = BigDecimal.valueOf(100);

        filters.checkFiltersParams(categoryId, minPrice, maxPrice);
        Mockito.verify(service).findAll(minPrice, maxPrice);

    }

    @Test
    void checkFiltersParamsWhenOnlyCategoryCallFindAllCategoryAndMinPrice() {

        Long categoryId = 2L;
        BigDecimal minPrice = BigDecimal.valueOf(0);
        BigDecimal maxPrice = null;

        filters.checkFiltersParams(categoryId, minPrice, maxPrice);
        Mockito.verify(service).findAll(categoryId, minPrice);

    }
}