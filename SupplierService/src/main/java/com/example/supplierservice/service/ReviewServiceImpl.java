package com.example.supplierservice.service;

import com.example.supplierservice.entity.Review;
import com.example.supplierservice.repository.ReviewRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ReviewServiceImpl implements ReviewService {

    ReviewRepository repository;

    @Override
    public Review create(Review review) {
        return repository.save(review);
    }
}
