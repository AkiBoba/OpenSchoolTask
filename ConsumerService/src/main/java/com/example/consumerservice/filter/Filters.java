package com.example.consumerservice.filter;

import com.example.consumerservice.domain.Product;
import com.example.consumerservice.service.ProductService;
import com.example.consumerservice.utilits.PrintProductsUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Filters {

    ProductService service;
    PrintProductsUtility utilit;

    /**
     * Метод получает список продуктов и возвращает отфильтрованный результат в соответствии с переданными параметрами фильтрации
     * @param categoryId категория
     * @param minPrice минимальная цена
     * @param maxPrice максимальная цена
     * @return список продуктов
     */
    public List<Product> checkFiltersParams(Long categoryId, BigDecimal minPrice, BigDecimal maxPrice) throws JsonProcessingException {
        List<Product> products = service.findAll();
        if (categoryId != null || minPrice != null || maxPrice != null) {
            products = service.findAll(categoryId, minPrice, maxPrice);
        }
        printProducts(products);
        return products;
    }

    private void printProducts(List<Product> products) {
        utilit.printProducts(products);
    }

}
