package com.one.eCommerce_backend.dtos;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDto {
    private CartProductDto cartProduct;
    private int quantity;
    private BigDecimal totalPrice;
}
