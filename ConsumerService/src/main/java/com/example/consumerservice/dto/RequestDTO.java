package com.example.consumerservice.dto;

import lombok.Data;

@Data
public class RequestDTO {
    private Integer offset;
    private Integer size;
    private String keyWord;
}
