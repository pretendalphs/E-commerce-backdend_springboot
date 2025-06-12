package com.one.eCommerce_backend.mappers;

import com.one.eCommerce_backend.dtos.CartDto;
import com.one.eCommerce_backend.dtos.CartItemDto;
import com.one.eCommerce_backend.entities.Cart;
import com.one.eCommerce_backend.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    @Mapping(source = "product", target = "cartProduct")
    CartItemDto toDto(CartItem cartItem);
}
