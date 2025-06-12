package com.one.eCommerce_backend.repositories;

import com.one.eCommerce_backend.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
}