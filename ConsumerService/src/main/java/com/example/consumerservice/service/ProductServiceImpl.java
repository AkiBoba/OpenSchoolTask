package com.example.consumerservice.service;

import com.example.consumerservice.domain.Product;
import com.example.consumerservice.dto.ProductDTO;
import com.example.consumerservice.dto.PageDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {
    RestTemplate restTemplate;
    static HttpHeaders headers = new HttpHeaders();
    static ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    static String productUrl = "http://localhost:8080/api/v1/products";
    static String pageableUrlSuffix = "/pageable";
    static String idUrlSuffix = "/{id}";

    @Override
    public Product create(ProductDTO product) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProductDTO> requestEntity = new HttpEntity<>(product, headers);
        ResponseEntity<Product> response = restTemplate.exchange(productUrl, HttpMethod.POST, requestEntity, Product.class);
        return response.getBody();
    }

    @Override
    public List<Product> findAll() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProductDTO> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<List<LinkedHashMap>> responseEntity = restTemplate.exchange(productUrl, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<>() {
        });
        return responseEntity.getBody().stream()
                .map(productData -> mapper.convertValue(productData, Product.class))
                .collect(Collectors.toList());
    }

    @Override
    public PageDTO findAll(Integer offset, Integer size, String keyWord) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        URI uri = UriComponentsBuilder.fromUriString(productUrl + pageableUrlSuffix)
                .queryParam("offset", offset)
                .queryParam("size", size)
                .queryParam("keyWord", keyWord)
                .build()
                .encode()
                .toUri();
        ResponseEntity<PageDTO> response = restTemplate.exchange(uri, HttpMethod.GET, entity, PageDTO.class);
        return response.getBody();
    }

    @Override
    public Product findById(Long id) throws HttpClientErrorException {
        Map<String, Long> vars = Collections.singletonMap("id", id);
        return restTemplate.getForObject(productUrl + idUrlSuffix, Product.class, vars);
    }

    @Override
    public void delete(Long id) {
        Map<String, Long> vars = Collections.singletonMap("id", id);
        restTemplate.delete(productUrl + idUrlSuffix, vars);
    }

    @Override
    public Product put(Long id, ProductDTO product) {
        Map<String, Long> vars = Collections.singletonMap("id", id);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProductDTO> requestEntity = new HttpEntity<>(product, headers);
        ResponseEntity<Product> response = restTemplate.exchange(productUrl + idUrlSuffix, HttpMethod.PUT, requestEntity, Product.class, vars);
        return response.getBody();
    }
}
