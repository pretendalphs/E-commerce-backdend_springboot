package com.one.eCommerce_backend.mappers;

import com.one.eCommerce_backend.dtos.UserDto;
import com.one.eCommerce_backend.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userToUserDto(User user);
}
