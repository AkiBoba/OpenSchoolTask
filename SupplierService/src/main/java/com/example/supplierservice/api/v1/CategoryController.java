package com.example.supplierservice.api.v1;

import com.example.supplierservice.entity.Category;
import com.example.supplierservice.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("api/v1/categories")
public class CategoryController {

    CategoryService service;

    @Operation(description = "Контроллер принимает запрос на запись новой категории в БД")
    @PostMapping
    public Category create(@Valid @RequestBody Category category) {
        return service.create(category);
    }

    @Operation(description = "Контроллер принимает запрос на получение списка всех категорий")
    @GetMapping
    public List<Category> findAll() {
        return service.findAll();
    }

    @Operation(description = "Контроллер принимает запрос на получение категории по идентификатору")
    @GetMapping("/{id}")
    public Category findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(description = "Контроллер принимает запрос на изменение категории по идентификатору")
    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody Category request) {
        service.findById(id);
        return service.create(request);
    }

    @Operation(description = "Контроллер принимает запрос на удаление категории по идентификатору")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
