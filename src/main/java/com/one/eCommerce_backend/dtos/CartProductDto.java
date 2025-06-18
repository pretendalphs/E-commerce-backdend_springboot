package com.one.eCommerce_backend.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartProductDto {
    private String id;
    private String name;
    private BigDecimal price;
}
