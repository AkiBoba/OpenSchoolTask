package com.example.consumerservice.service;

import com.example.consumerservice.domain.Category;
import com.example.consumerservice.dto.ProductDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CategoryServiceImpl implements CategoryService {

    RestTemplate restTemplate;

    static HttpHeaders headers = new HttpHeaders();

    static ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    static String categoryUrl = "http://localhost:8080/api/v1/categories";

    @Override
    public List<Category> findAll() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProductDTO> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<List<LinkedHashMap>> responseEntity = restTemplate.exchange(categoryUrl, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<>() {
        });
        return responseEntity.getBody().stream()
                .map(categoryData -> mapper.convertValue(categoryData, Category.class))
                .collect(Collectors.toList());
    }
}
