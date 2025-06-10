package com.one.eCommerce_backend.dtos;


import lombok.Data;

@Data
public class UpdateProductRequest {
    String name;
    String description;
    Double price;
}
