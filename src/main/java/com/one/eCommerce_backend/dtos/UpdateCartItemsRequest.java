package com.one.eCommerce_backend.dtos;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCartItemsRequest {
    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quatity must be at least 1")
    @Max(value = 100, message = "Quantity must be at most 100")
    private Integer quantity;

}
