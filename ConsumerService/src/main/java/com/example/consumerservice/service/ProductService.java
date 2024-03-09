package com.example.consumerservice.service;

import com.example.consumerservice.domain.Product;
import com.example.consumerservice.dto.PageDTO;
import com.example.consumerservice.dto.ProductDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ProductService {
    /**
     * Создать новый продукт.
     * @param product объект класса product
     * @return сохраненный product
     */
    Product create(ProductDTO product);

    /**
     * Получить список всех продуктов.
     * @return список всех продуктов.
     */
    List<Product> findAll();

    /**
     * Получить список всех продуктов.
     * @param offset номер старицы
     * @param size количество получаемых результатов
     * @param keyWord ключевое слово для фильтрации результата
     * @return постраничный список всех продуктов.
     */
    PageDTO<Product> findAll(Integer offset, Integer size, String keyWord) throws JsonProcessingException;

    /**
     * Получить информацию о продукте по его идентификатору.
     * @param id идентификатор продукта
     * @return объект класса product
     */
    Product findById(Long id);

    /**
     * Удалить продукт по его идентификатору.
     * @param id идентификатор продукта
     */
    void delete(Long id);

    /**
     * Изменить продукт по его идентификатору.
     * @param id идентификатор продукта
     */
    Product put(Long id, ProductDTO product);

}
