package com.example.supplierservice.api.v1;

import com.example.supplierservice.dto.ReviewDTO;
import com.example.supplierservice.entity.Product;
import com.example.supplierservice.service.ProductService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReviewControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductService service;

    @Container
    static PostgreSQLContainer<?> postgres = IntegrationTest.postgres;

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        IntegrationTest.configureProperties(registry);
    }

    @Test
    @Transactional
    @SneakyThrows
    void whenCreateReviewThenReturnProductReviewListOneMore() {
        Product product = service.findById(1L);

        assertEquals(4, product.getReviews().size());

        ReviewDTO ratingExpected = ReviewDTO
                .builder()
                .review("new Review")
                .productId(1L)
                .build();

        String json = new ObjectMapper().writeValueAsString(ratingExpected);

        mockMvc.perform(post("/api/v1/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        product = service.findById(1L);

        assertEquals(5, product.getReviews().size());

    }
}