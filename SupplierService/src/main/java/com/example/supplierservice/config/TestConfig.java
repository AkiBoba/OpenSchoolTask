package com.example.supplierservice.config;

import com.example.supplierservice.entity.Category;
import com.example.supplierservice.entity.Product;
import com.example.supplierservice.entity.Rating;
import com.example.supplierservice.entity.Review;
import com.example.supplierservice.repository.CategoryRepository;
import com.example.supplierservice.repository.ProductRepository;
import com.example.supplierservice.repository.RatingRepository;
import com.example.supplierservice.repository.ReviewRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class TestConfig {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final RatingRepository ratingRepository;
    private final ReviewRepository reviewRepository;

    @PostConstruct
    private void init() {

        Product product1 = Product
                .builder()
                .name("яблоки")
                .description("зеленые")
                .price(new BigDecimal("10.20"))
                .build();

        Product product2 = Product
                .builder()
                .name("груши")
                .description("сезонные")
                .price(new BigDecimal("15.10"))
                .build();

        Product product3 = Product
                .builder()
                .name("помидоры")
                .description("черри")
                .price(new BigDecimal("220.60"))
                .build();

        Product product4 = Product
                .builder()
                .name("огурцы")
                .description("тепличные")
                .price(new BigDecimal("230.00"))
                .build();

        Product product5 = Product
                .builder()
                .name("сок")
                .description("виноградный")
                .price(new BigDecimal("50.10"))
                .build();

        Product product6 = Product
                .builder()
                .name("вода")
                .description("минеральная")
                .price(new BigDecimal("135.69"))
                .build();

        Product product7 = Product
                .builder()
                .name("молоко")
                .description("жирное")
                .price(new BigDecimal("95.20"))
                .build();

        Product product8 = Product
                .builder()
                .name("творог")
                .description("обезжиренный")
                .price(new BigDecimal("100.30"))
                .build();

        Product product9 = Product
                .builder()
                .name("греча")
                .description("импортная")
                .price(new BigDecimal("60.50"))
                .build();

        Product product10 = Product
                .builder()
                .name("рис")
                .description("белый")
                .price(new BigDecimal("70.10"))
                .build();

        Category category1 = Category
                .builder()
                .name("фрукты")
                .products(List.of(product1, product2))
                .build();

        Category category2 = Category
                .builder()
                .name("овощи")
                .products(List.of(product3, product4))
                .build();

        Category category3 = Category
                .builder()
                .name("напитки")
                .products(List.of(product5, product6))
                .build();

        Category category4 = Category
                .builder()
                .name("молочка")
                .products(List.of(product7, product8))
                .build();

        Category category5 = Category
                .builder()
                .name("крупы")
                .products(List.of(product9, product10))
                .build();

        categoryRepository.saveAll(List.of(category1, category2, category3, category4, category5));

        product1 =  productRepository.findById(1L).get();

        product2 =  productRepository.findById(2L).get();

        product3 =  productRepository.findById(3L).get();

        product4 =  productRepository.findById(4L).get();

        product5 =  productRepository.findById(5L).get();

        product6 =  productRepository.findById(6L).get();

        product7 =  productRepository.findById(7L).get();

        product8 =  productRepository.findById(8L).get();

        product9 =  productRepository.findById(9L).get();

        product10 =  productRepository.findById(10L).get();

        Rating rating1 = ratingRepository.save(

                Rating.builder()
                        .rating(3)
                        .build()
        );

        Rating rating2 = ratingRepository.save(Rating.builder()
                .rating(5)
                .build()
        );

        Rating rating3 = ratingRepository.save(Rating.builder()
                .rating(4)
                .build()
        );

        Rating rating4 = ratingRepository.save(Rating.builder()
                .rating(3)
                .build()
        );

        Rating rating5 = ratingRepository.save(Rating.builder()
                .rating(5)
                .build()
        );

        Rating rating6 = ratingRepository.save(Rating.builder()
                .rating(4)
                .build()
        );

        Rating rating7 = ratingRepository.save(Rating.builder()
                .rating(3)
                .build()
        );

        Rating rating8 = ratingRepository.save(Rating.builder()
                .rating(5)
                .build()
        );

        Rating rating9 = ratingRepository.save(Rating.builder()
                .rating(4)
                .build()
        );

        Rating rating10 = ratingRepository.save(Rating.builder()
                .rating(3)
                .build()
        );

        Rating rating11 = ratingRepository.save(Rating.builder()
                .rating(5)
                .build()
        );

        Rating rating12 = ratingRepository.save(Rating.builder()
                .rating(4)
                .build()
        );

        Rating rating13 = ratingRepository.save(Rating.builder()
                .rating(3)
                .build()
        );

        Rating rating14 = ratingRepository.save(Rating.builder()
                .rating(5)
                .build()
        );

        Rating rating15 = ratingRepository.save(Rating.builder()
                .rating(4)
                .build()
        );

        Rating rating16 = ratingRepository.save(Rating.builder()
                .rating(3)
                .build()
        );

        Rating rating17 = ratingRepository.save(Rating.builder()
                .rating(4)
                .build()
        );

        Rating rating18 = ratingRepository.save(Rating.builder()
                .rating(4)
                .build()
        );

        Rating rating19 = ratingRepository.save(Rating.builder()
                .rating(1)
                .build()
        );

        Rating rating20 = ratingRepository.save(Rating.builder()
                .rating(2)
                .build()
        );

        Rating rating21 = ratingRepository.save(Rating.builder()
                .rating(4)
                .build()
        );

        Rating rating22 = ratingRepository.save(Rating.builder()
                .rating(1)
                .build()
        );

        Rating rating23 = ratingRepository.save(Rating.builder()
                .rating(5)
                .build()
        );

        Rating rating24 = ratingRepository.save(Rating.builder()
                .rating(2)
                .build()
        );

        Rating rating25 = ratingRepository.save(Rating.builder()
                .rating(3)
                .build()
                );

        Rating rating26 = ratingRepository.save(Rating.builder()
                .rating(5)
                .build()
                );

        Rating rating27 = ratingRepository.save(Rating.builder()
                .rating(5)
                .build()
                );

        Rating rating28 = ratingRepository.save(Rating.builder()
                .rating(5)
                .build()
        );

        Rating rating29 = ratingRepository.save(Rating.builder()
                .rating(5)
                .build()
        );

        Rating rating30 = ratingRepository.save(Rating.builder()
                .rating(1)
                .build()
        );

        Rating rating31 = ratingRepository.save(Rating.builder()
                .rating(3)
                .build()
        );

        Rating rating32 = ratingRepository.save(Rating.builder()
                .rating(2)
                .build()
        );

        Rating rating33 = ratingRepository.save(Rating.builder()
                .rating(4)
                .build()
        );

        Review review1 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review2 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review3 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review4 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review5 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review6 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review7 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review8 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review9 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review10 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review11 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review12 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review13 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review14 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review15 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review16 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review17 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review18 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review19 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review20 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review21 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review22 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review23 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review24 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review25 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review26 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review27 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review28 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review29 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review30 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review31 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review32 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        Review review33 = reviewRepository.save(Review.builder()
                .review("новый отзыв")
                .build()
        );

        product1.setRatings(List.of(rating1, rating2, rating3, rating31));
        product2.setRatings(List.of(rating4, rating5, rating6, rating32));
        product3.setRatings(List.of(rating7, rating8, rating9));
        product4.setRatings(List.of(rating12, rating11, rating10));
        product5.setRatings(List.of(rating13, rating14, rating15));
        product6.setRatings(List.of(rating18, rating17, rating16));
        product7.setRatings(List.of(rating19, rating20, rating21));
        product8.setRatings(List.of(rating24, rating23, rating22));
        product9.setRatings(List.of(rating25, rating26, rating27));
        product10.setRatings(List.of(rating30, rating29, rating28, rating33));

        product1.setReviews(List.of(review1, review2, review3, review31));
        product2.setReviews(List.of(review4, review5, review6, review32));
        product3.setReviews(List.of(review7, review8, review9));
        product4.setReviews(List.of(review12, review11, review10));
        product5.setReviews(List.of(review13, review14, review15));
        product6.setReviews(List.of(review18, review17, review16));
        product7.setReviews(List.of(review19, review20, review21));
        product8.setReviews(List.of(review24, review23, review22));
        product9.setReviews(List.of(review25, review26, review27));
        product10.setReviews(List.of(review30, review29, review28, review33));

        productRepository.saveAll(List.of(product1, product2, product3, product4, product5, product6, product7, product8, product9, product10));

    }

}
