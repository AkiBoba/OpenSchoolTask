package com.example.consumerservice.service;

import com.example.consumerservice.domain.Category;

import java.util.List;

public interface CategoryService {

    /**
     * Получить список всех категорий.
     * @return  список всех категорий.
     */
    List<Category> findAll();
}
