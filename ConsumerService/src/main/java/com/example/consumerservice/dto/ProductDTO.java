package com.example.consumerservice.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductDTO {

    private Long id;

    @NotBlank(message = "Поле имя не заполнено")
    private String name;

    @NotBlank(message = "Поле описание не заполнено")
    private String description;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @NotNull(message = "Поле категория не заполнено")
    private Long categoryId;
}
