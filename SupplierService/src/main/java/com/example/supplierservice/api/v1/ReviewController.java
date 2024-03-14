package com.example.supplierservice.api.v1;

import com.example.supplierservice.dto.ReviewDTO;
import com.example.supplierservice.entity.Product;
import com.example.supplierservice.entity.Review;
import com.example.supplierservice.service.ProductService;
import com.example.supplierservice.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("api/v1/reviews")
public class ReviewController {

    ReviewService reviewService;
    ProductService productService;

    @Operation(description = "Контроллер принимает запрос на создание отзыва на продукт")
    @PostMapping
    public void create(@Valid @RequestBody ReviewDTO reviewDTO) {
        createAndSetReview(reviewDTO);
    }

    private void createAndSetReview(ReviewDTO reviewDTO) {
        Review review = reviewService.create(Review.builder()
                .review(reviewDTO.getReview())
                .build());
        Product product = productService.findById(reviewDTO.getProductId());
        List<Review> list = product.getReviews();
        list.add(review);
        product.setReviews(list);
        productService.create(product);
    }

}
