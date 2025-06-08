package com.one.eCommerce_backend.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class UserDto {
    public Long id;
    private String name;
    private String email;

}
