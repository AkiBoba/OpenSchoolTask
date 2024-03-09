package com.example.supplierservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestDTO {
    private Integer offset;
    private Integer size;
    private String keyWord;
}
