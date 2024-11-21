package com.example.supplierservice.api.v1;

import com.example.supplierservice.entity.Category;
import com.example.supplierservice.service.CategoryService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CategoryService service;
    private final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Container
    static PostgreSQLContainer<?> postgres = IntegrationTest.postgres;

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        IntegrationTest.configureProperties(registry);
    }

    @Test
    @SneakyThrows
    @Order(1)
    public void whenGetByIdThenReturnCategory() {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/categories/{id}", 1))
                .andExpect(status()
                        .isOk()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        Category category = objectMapper.readValue(res, Category.class);
        Category categoryExpected = service.findById(1L);
        assertEquals(categoryExpected.getName(), category.getName());
        assertEquals(categoryExpected.getId(), category.getId());

    }

    @Test
    @SneakyThrows
    @Order(2)
    void whenFindAllThenReturnCategorysList() {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/categories"))
                .andExpect(status()
                        .isOk()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        var categories = objectMapper.readValue(res, List.class);
        assertThat(categories.size() == 3);

    }

    @Test
    @SneakyThrows
    @Order(3)
    void whenUpdateThenReturnUpdatedCategory() {
        Category categoryExpected = Category
                .builder()
                .id(3L)
                .name("tea")
                .build();
        String json = new ObjectMapper().writeValueAsString(categoryExpected);
        String nameExpected = "tea";
        MvcResult mvcResult = mockMvc.perform(put("/api/v1/categories/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        var category = objectMapper.readValue(res, Category.class);
        assertEquals(nameExpected, category.getName());
    }

    @Test
    @SneakyThrows
    @Order(4)
    void whenDeleteThenReturnDeletedCategory() {
        mockMvc.perform(delete("/api/v1/categories/{id}", 1L))
                .andExpect(status()
                        .isOk()).andReturn();

        mockMvc.perform(get("/api/v1/categories/{id}", 1L))
                .andExpect(status()
                        .is4xxClientError());

    }
}