package com.example.supplierservice.service;

import com.example.supplierservice.entity.Rating;
import com.example.supplierservice.repository.RatingRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RatingServiceImpl implements RatingService {

    RatingRepository repository;

    @Override
    public Rating create(Rating rating) {
        return repository.save(rating);
    }
}
