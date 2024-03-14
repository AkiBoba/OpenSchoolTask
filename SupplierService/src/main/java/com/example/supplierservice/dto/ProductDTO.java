package com.example.supplierservice.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductDTO {

    @NotBlank(message = "Поле имя не заполнено")
    private String name;

    @NotBlank(message = "Поле описание не заполнено")
    private String description;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @NotNull(message = "Поле категория не заполнено")
    private Long categoryId;
}
