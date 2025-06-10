package com.one.eCommerce_backend.dtos;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String oldPassword, newPassword;
}
