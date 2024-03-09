package com.example.consumerservice.props;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "product.page")
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
@Data
public class ProductProps {

    @Min(value = 1, message = "must be between 1 and 25")
    @Max(value = 25, message = "must be between 1 and 25")
    private int pageSize = 5;
}
