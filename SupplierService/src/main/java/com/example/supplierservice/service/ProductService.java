package com.example.supplierservice.service;

import com.example.supplierservice.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    /**
     * Создать новый продукт.
     * @param product объект класса product
     * @return сохраненный product
     */
    Product create(Product product);

    /**
     * Получить список всех продуктов.
     * @return постраничный список всех продуктов.
     */
    List<Product> findAll();

    /**
     * Получить список всех продуктов.
     * @param pageable параметры пагинации результата
     * @return постраничный список всех продуктов.
     */
    Page<Product> findAll(Pageable pageable);

    /**
     * Получить список всех продуктов.
     * @param pageable параметры пагинации результата
     * @param keyWord ключевое слово для фильтрации результата
     * @return постраничный список найденных продуктов
     */
    Page<Product> findAll(Pageable pageable, String keyWord);

    /**
     * Получить информацию о продукте по его идентификатору.
     * @param productId идентификатор продукта
     * @return объект класса product
     */
    Product findById(Long productId);

    /**
     * Удалить продукт по его идентификатору.
     * @param productId идентификатор продукта
     */
    void delete(Long productId);

}
