package com.one.eCommerce_backend.mappers;

import com.one.eCommerce_backend.dtos.CartDto;
import com.one.eCommerce_backend.entities.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDto toDto(Cart cart);

}
