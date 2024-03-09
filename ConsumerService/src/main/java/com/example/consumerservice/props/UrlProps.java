package com.example.consumerservice.props;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("product.api")
@Data
public class UrlProps {

    //    @Value("${product.api.productUrl}")
    String productUrl;

    public UrlProps() {
    }

    public UrlProps(String productUrl) {
        this.productUrl = productUrl;
    }
}
