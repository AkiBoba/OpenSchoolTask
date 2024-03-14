package com.example.supplierservice.api.v1;

import com.example.supplierservice.dto.RatingDTO;
import com.example.supplierservice.entity.Product;
import com.example.supplierservice.entity.Rating;
import com.example.supplierservice.service.ProductService;
import com.example.supplierservice.service.RatingService;
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
@RequestMapping("api/v1/ratings")
public class RatingController {

    RatingService ratingService;
    ProductService productService;

    @Operation(description = "Контроллер принимает запрос на создание и добавление оценки продукта")
    @PostMapping
    public void create(@Valid @RequestBody RatingDTO ratingDTO) {
        createAndSetRating(ratingDTO);
    }

    private void createAndSetRating(RatingDTO ratingDTO) {
        Rating rating = ratingService.create(Rating.builder()
                .rating(ratingDTO.getRating())
                .build());
        Product product = productService.findById(ratingDTO.getProductId());
        List<Rating> list = product.getRatings();
        list.add(rating);
        product.setRatings(list);
        productService.create(product);
    }

}
