package com.example.supplierservice.api.v1;

import com.example.supplierservice.dto.ProductDTO;
import com.example.supplierservice.dto.ProductListWrapper;
import com.example.supplierservice.entity.Product;
import com.example.supplierservice.service.ProductService;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
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
class ProductControllerIntegrationTest extends IntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductService service;
    private final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


    @Container
    static PostgreSQLContainer<?> postgres = IntegrationTest.postgres;

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        IntegrationTest.configureProperties(registry);
    }

    @Test
    @SneakyThrows
    @Transactional
    @Order(1)
    public void whenGetByIdThenReturnProduct() {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/products/{id}", 1))
                .andExpect(status()
                        .isOk()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        Product product = objectMapper.readValue(res, Product.class);
        Product productExpected = service.findById(1L);
        assertEquals(productExpected.getName(), product.getName());
        assertEquals(productExpected.getPrice(), product.getPrice());
        assertEquals(productExpected.getCategory().getName(), product.getCategory().getName());

    }

    @Test
    @SneakyThrows
    @Order(2)
    void whenCreateProductThenReturnProduct() {
        ProductDTO productExpected = ProductDTO
                .builder()
                .name("pear")
                .description("green")
                .price(new BigDecimal("10.20"))
                .categoryId(1L)
                .build();

        String json = new ObjectMapper().writeValueAsString(productExpected);

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();


        String res = mvcResult.getResponse().getContentAsString();
        Product product = objectMapper.readValue(res, Product.class);

        assertEquals(productExpected.getName(), product.getName());
        assertEquals(productExpected.getPrice(), product.getPrice());

    }

    @Test
    @SneakyThrows
    @Order(3)
    void whenFindAllThenReturnProductsList() {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/products/pageable"))
                .andExpect(status()
                        .isOk()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        var products = objectMapper.readValue(res, ProductListWrapper.class).getContent();
        assertThat(products.size() == 5);

    }

    @Test
    @SneakyThrows
    @Order(4)
    void whenFindPageableThenReturnProductsListPageable() {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("offset", "0");
        requestParams.add("size", "3");
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/products/pageable").params(requestParams))
                .andExpect(status()
                        .isOk()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        var products = objectMapper.readValue(res, ProductListWrapper.class).getContent();
        assertThat(products.size() == 3);
    }

    @Test
    @SneakyThrows
    @Order(5)
    void whenFindPageableAndByKeyWordThenReturnProductsListPageable() {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("offset", "0");
        requestParams.add("size", "3");
        requestParams.add("keyWord", "apple");
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/products/pageable").params(requestParams))
                .andExpect(status()
                        .isOk()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        var products = objectMapper.readValue(res, ProductListWrapper.class).getContent();
        assertThat(products.size() == 1);
    }

    @Test
    @SneakyThrows
    @Order(6)
    void whenFindFilteredThenReturnProductsListFiltered() {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("categoryId", "1");
        requestParams.add("minPrice", "0");
        requestParams.add("maxPrice", "15");
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/products/filtered").params(requestParams))
                .andExpect(status()
                        .isOk()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        var products = objectMapper.readValue(res, List.class);
        assertThat(products.size() == 1);
    }

    @Test
    @SneakyThrows
    @Order(7)
    void whenFindFilteredByCategoryThenReturnProductsListFiltered() {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("categoryId", "2");
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/products/filtered").params(requestParams))
                .andExpect(status()
                        .isOk()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        var products = objectMapper.readValue(res, List.class);
        assertThat(products.size() == 2);
    }

    @Test
    @SneakyThrows
    @Order(8)
    void whenFindFilteredByPriceThenReturnProductsListFiltered() {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("minPrice", "10");
        requestParams.add("maxPrice", "100");
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/products/filtered").params(requestParams))
                .andExpect(status()
                        .isOk()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        var products = objectMapper.readValue(res, List.class);
        assertThat(products.size() == 3);
    }

    @Test
    @SneakyThrows
    @Order(9)
    void whenFindByIdThenReturnOneProduct() {
        String nameExpected = "apple";
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/products/{id}", 1L))
                .andExpect(status()
                        .isOk()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        var product = objectMapper.readValue(res, Product.class);
        assertEquals(nameExpected, product.getName());
    }

    @Test
    @SneakyThrows
    @Order(10)
    void whenUpdateThenReturnUpdatedProduct() {
        ProductDTO productExpected = ProductDTO
                .builder()
                .name("peach")
                .description("green")
                .price(new BigDecimal("10.20"))
                .categoryId(1L)
                .build();
        String json = new ObjectMapper().writeValueAsString(productExpected);
        String nameExpected = "peach";
        String categoryExpected = "fruits";
        MvcResult mvcResult = mockMvc.perform(put("/api/v1/products/{id}", 3L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        var product = objectMapper.readValue(res, Product.class);
        assertEquals(nameExpected, product.getName());
        assertEquals(categoryExpected, product.getCategory().getName());
    }

    @Test
    @SneakyThrows
    @Order(11)
    void whenDeleteThenReturnDeletedProduct() {
        mockMvc.perform(delete("/api/v1/products/{id}", 1L))
                .andExpect(status()
                        .isOk()).andReturn();

        mockMvc.perform(get("/api/v1/products/{id}", 1L))
                .andExpect(status()
                        .is4xxClientError());

    }
}