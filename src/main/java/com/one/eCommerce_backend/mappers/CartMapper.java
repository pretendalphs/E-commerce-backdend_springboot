package com.one.eCommerce_backend.mappers;

import com.one.eCommerce_backend.dtos.CartDto;
import com.one.eCommerce_backend.dtos.CartItemDto;
import com.one.eCommerce_backend.entities.Cart;
import com.one.eCommerce_backend.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
    @Mapping(target = "items", source = "items")
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}
