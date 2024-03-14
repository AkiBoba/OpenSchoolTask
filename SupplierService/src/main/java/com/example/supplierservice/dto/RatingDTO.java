package com.example.supplierservice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RatingDTO {

    @NotNull(message = "Поле оценка не заполнено")
    @Min(value = 1, message = "оценка должна быть от 1 до 5")
    @Max(value = 5, message = "оценка должна быть от 1 до 5")
    private Integer rating;

    @NotNull(message = "Поле продукт не заполнено")
    private Long productId;
}
