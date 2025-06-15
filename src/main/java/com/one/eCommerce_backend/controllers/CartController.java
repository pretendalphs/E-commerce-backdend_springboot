package com.one.eCommerce_backend.controllers;


import com.one.eCommerce_backend.dtos.AddItemToCartRequest;
import com.one.eCommerce_backend.dtos.CartDto;
import com.one.eCommerce_backend.dtos.CartItemDto;
import com.one.eCommerce_backend.dtos.UpdateCartItemsRequest;
import com.one.eCommerce_backend.exceptions.CartNotFoundException;
import com.one.eCommerce_backend.exceptions.ProductNotFoundException;
import com.one.eCommerce_backend.services.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartDto> createCart(
            UriComponentsBuilder uriComponentsBuilder
    ) {
        var cartDto = cartService.createCart();
        var uri = uriComponentsBuilder.path("/carts/{id}")
                .buildAndExpand(cartDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(cartDto);

    }

    @PostMapping("/{cardId}/items")
    public ResponseEntity<CartItemDto> addItemToCart(
            @PathVariable UUID cardId,
            @RequestBody AddItemToCartRequest request
    ) {
        var cartItemDto = cartService.addToCart(cardId, request.getProductId());
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
    }

    @GetMapping("/{cartId}")
    public CartDto getCart(
            @PathVariable UUID cartId
    ) {
        return cartService.getCart(cartId);

    }

    @PutMapping("/{cartId}/items/{productId}")
    public CartItemDto updateCartItems(
            @PathVariable UUID cartId,
            @PathVariable Long productId,
            @RequestBody UpdateCartItemsRequest request
    ) {
        return cartService.updateCartItems(cartId, productId, request.getQuantity());


    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> deleteCartItem(
            @PathVariable("cartId") UUID cartId,
            @PathVariable("productId") Long productId
    ) {
        cartService.deleteCartItem(cartId, productId);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCart(
            @PathVariable UUID cartId
    ) {
        cartService.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCartNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("error", "Cart not found")
        );
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFount() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("error", "Product not found")
        );
    }

}