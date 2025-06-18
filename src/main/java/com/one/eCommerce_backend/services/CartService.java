package com.one.eCommerce_backend.services;

import com.one.eCommerce_backend.dtos.CartDto;
import com.one.eCommerce_backend.dtos.CartItemDto;
import com.one.eCommerce_backend.entities.Cart;
import com.one.eCommerce_backend.exceptions.CartNotFoundException;
import com.one.eCommerce_backend.exceptions.ProductNotFoundException;
import com.one.eCommerce_backend.mappers.CartMapper;
import com.one.eCommerce_backend.repositories.CartRepository;
import com.one.eCommerce_backend.repositories.ProductRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CartService {

    private CartRepository cartRepository;
    private CartMapper cartMapper;
    private ProductRepository productRepository;

    public CartDto createCart() {
        var cart = new Cart();
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    public CartItemDto addToCart(UUID cartId, Long productId) {
        var cart = cartRepository.findById(cartId)
                .orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }
        var product = productRepository.findById(productId)
                .orElse(null);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        var cartItem = cart.addItem(product);
        cartRepository.save(cart);
        return cartMapper.toDto(cartItem);
    }

    public CartDto getCart(UUID cartId) {
        var cart = cartRepository.getCartWithItems(cartId)
                .orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }
        return cartMapper.toDto(cart);
    }

    public CartItemDto updateCartItems(UUID cartId, Long productId, Integer quantity) {
        var cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }
        var cartItem = cart.getItem(productId);
        if (cartItem == null) {
            throw new ProductNotFoundException();
        }

        cartItem.setQuantity(quantity);
        cartRepository.save(cart);
        return cartMapper.toDto(cartItem);
    }

    public void deleteCartItem(UUID cartId, Long productId) {
        var cart = cartRepository.getCartWithItems(cartId)
                .orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }
        cart.removeItem(productId);
        cartRepository.save(cart);
    }

    public void clearCart(UUID cartId) {
        var cart = cartRepository.findById(cartId)
                .orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }
        cart.clear();
        cartRepository.save(cart);
    }
}
