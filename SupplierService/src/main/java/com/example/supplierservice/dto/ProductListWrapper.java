package com.example.supplierservice.dto;

import com.example.supplierservice.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductListWrapper {
    private List<Product> content;

}
