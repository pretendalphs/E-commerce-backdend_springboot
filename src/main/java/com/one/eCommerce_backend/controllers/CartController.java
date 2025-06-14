package com.one.eCommerce_backend.controllers;


import com.one.eCommerce_backend.dtos.AddItemToCartRequest;
import com.one.eCommerce_backend.dtos.CartDto;
import com.one.eCommerce_backend.dtos.CartItemDto;
import com.one.eCommerce_backend.entities.Cart;
import com.one.eCommerce_backend.entities.CartItem;
import com.one.eCommerce_backend.mappers.CartMapper;
import com.one.eCommerce_backend.repositories.CartRepository;
import com.one.eCommerce_backend.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<CartDto> createCart(
            UriComponentsBuilder uriComponentsBuilder
    ) {
        var cart = new Cart();
        cartRepository.save(cart);
        var cartDto = cartMapper.toDto(cart);
        var uri = uriComponentsBuilder.path("/carts/{id}")
                .buildAndExpand(cart.getId())
                .toUri();
        return ResponseEntity.created(uri).body(cartDto);

    }

    @PostMapping("/{cardId}/items")
    public ResponseEntity<CartItemDto> addItemToCart(
            @PathVariable UUID cardId,
            @RequestBody AddItemToCartRequest request
    ) {
        var cart = cartRepository.findById(cardId)
                .orElse(null);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        var product = productRepository.findById(request.getProductId())
                .orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        var cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cart.getItems().add(cartItem);
        }
        cartRepository.save(cart);
        var cartItemDto = cartMapper.toDto(cartItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCart(
            @PathVariable UUID cartId
    ) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cartId == null) {
            return ResponseEntity.notFound().build();
        }
        var cartDto = cartMapper.toDto(cart);
        return ResponseEntity.ok(cartDto);

    }
}
