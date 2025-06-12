package com.one.eCommerce_backend.mappers;

import com.one.eCommerce_backend.dtos.CartItemDto;
import com.one.eCommerce_backend.entities.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemDto cartItemToCartItemDto(CartItem cartItem);
}
