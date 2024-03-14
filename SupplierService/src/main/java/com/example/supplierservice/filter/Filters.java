package com.example.supplierservice.filter;

import com.example.supplierservice.entity.Product;
import com.example.supplierservice.service.ProductService;
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

    /**
     * Метод получает список продуктов и возвращает отфильтрованный результат в соответствии с переданными параметрами фильтрации
     * @param categoryId категория
     * @param minPrice минимальная цена
     * @param maxPrice максимальная цена
     * @return список продуктов
     */
    public List<Product> checkFiltersParams(Long categoryId, BigDecimal minPrice, BigDecimal maxPrice) {
        if (categoryId != null && maxPrice != null) {
            return service.findAll(categoryId, minPrice, maxPrice);
        }
        if (maxPrice != null) {
            return service.findAll(minPrice, maxPrice);
        }
        if (categoryId != null) {
            return service.findAll(categoryId, minPrice);
        }
        return service.findAll(minPrice);
    }

}
