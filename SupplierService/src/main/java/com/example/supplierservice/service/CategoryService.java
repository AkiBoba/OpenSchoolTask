package com.example.supplierservice.service;

import com.example.supplierservice.entity.Category;

import java.util.List;

public interface CategoryService {

    /**
     *  Создать новую категорию.
     * @param category категория
     * @return сохраненный объект класса категория
     */
    Category create(Category category);

    /**
     * Получить список всех категорий.
     * @return  список всех категорий.
     */
    List<Category> findAll();

    /**
     * Получить информацию о категории по ее идентификатору.
     * @param id идентификатор категории
     * @return объект класса категория
     */
    Category findById(Long id);

    /**
     * Удалить категорию по ее идентификатору.
     * @param id идентификатор категории
     */
    void delete(Long id);

}
