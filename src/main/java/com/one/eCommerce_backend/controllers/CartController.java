package com.one.eCommerce_backend.controllers;


import com.one.eCommerce_backend.dtos.CartDto;
import com.one.eCommerce_backend.entities.Cart;
import com.one.eCommerce_backend.mappers.CartMapper;
import com.one.eCommerce_backend.repositories.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

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


}
