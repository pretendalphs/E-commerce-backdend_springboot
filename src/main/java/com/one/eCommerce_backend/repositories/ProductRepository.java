package com.one.eCommerce_backend.repositories;

import com.one.eCommerce_backend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}