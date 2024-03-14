package com.example.supplierservice.service;

import com.example.supplierservice.entity.Rating;

public interface RatingService {

    /**
     * Метод сохраняет новую оценку в БД
     * @param rating новая оценка
     */
    Rating create(Rating rating);
}
