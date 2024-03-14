package com.example.consumerservice.utilits;

import com.example.consumerservice.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PrintProductsUtilityImpl implements PrintProductsUtility {
    @Override
    public void printProducts(List<Product> products) {
        products.stream().forEach(product -> log.info(product.toString()));
    }
}
