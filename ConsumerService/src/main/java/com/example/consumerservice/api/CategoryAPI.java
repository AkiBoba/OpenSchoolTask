package com.example.consumerservice.api;

import com.example.consumerservice.domain.Category;
import com.example.consumerservice.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CategoryAPI {
    CategoryService service;

    @Operation(description = "Контроллер принимает запрос на получение списка всех категорий")
    @GetMapping("/categories")
    public List<Category> findAll() {
        return service.findAll();
    }
}
