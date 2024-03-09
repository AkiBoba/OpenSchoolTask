package com.example.consumerservice.filter;

import com.example.consumerservice.domain.Product;
import com.example.consumerservice.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Filters {

    ProductService service;

    /**
     * Метод получает список продуктов и возвращает отфильтрованный результат в соответствии с переданными параметрами фильтрации
     * @param categoryId категория
     * @param minPrice минимальная цена
     * @param maxPrice максимальная цена
     * @return список продуктов
     */
    public List<Product> checkFiltersParams(Long categoryId, BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> products = service.findAll();
        if (categoryId != null) {
            products = filteredByCategory(products, categoryId);
        }
        if (minPrice != null || maxPrice != null) {
            return filteredByPrice(products, minPrice, maxPrice);
        }
        return products;
    }

    private List<Product> filteredByCategory(List<Product> products, Long categoryId) {
        return products.stream().filter(product -> Objects.equals(product.getCategory().getId(), categoryId)).collect(Collectors.toList());
    }

    private List<Product> filteredByPrice(List<Product> products, BigDecimal min, BigDecimal max) {
        if (min != null && max != null) {
            return products.stream().filter(product -> product.getPrice().compareTo(min) > 0).filter(product -> product.getPrice().compareTo(max) < 0).collect(Collectors.toList());
        }
        if (max != null) {
            return products.stream().filter(product -> product.getPrice().compareTo(max) < 0).collect(Collectors.toList());
        }
        return products.stream().filter(product -> product.getPrice().compareTo(min) > 0).collect(Collectors.toList());
    }
}
