package com.example.supplierservice.service;

import com.example.supplierservice.entity.Review;

public interface ReviewService {

    /**
     * Метод сохраняет новый отзыв в БД
     * @param review новый отзыв
     */
    Review create(Review review);

}
