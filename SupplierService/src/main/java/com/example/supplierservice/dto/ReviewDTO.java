package com.example.supplierservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReviewDTO {

    @Size(min = 5, max = 2000, message = "Отзыв должен быть не меньше 50 и не больше 2000 символов")
    private String review;

    @NotNull(message = "Поле продукт не заполнено")
    private Long productId;
}
