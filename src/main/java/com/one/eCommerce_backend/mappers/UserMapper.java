package com.one.eCommerce_backend.mappers;

import com.one.eCommerce_backend.dtos.RegisterUserRequest;
import com.one.eCommerce_backend.dtos.UpdateUserRequest;
import com.one.eCommerce_backend.dtos.UserDto;
import com.one.eCommerce_backend.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userToUserDto(User user);

    User createUserDto(RegisterUserRequest request);

    void updateUser(UpdateUserRequest request, @MappingTarget User userDto);

}
