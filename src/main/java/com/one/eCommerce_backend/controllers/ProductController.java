package com.one.eCommerce_backend.controllers;

import com.one.eCommerce_backend.dtos.ProductDto;
import com.one.eCommerce_backend.entities.Product;
import com.one.eCommerce_backend.mappers.ProductMapper;
import com.one.eCommerce_backend.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductDto> getProducts(
            @RequestParam(required = false, defaultValue = "", name = "categoryId") Byte categoryId
    ) {
        List<Product> products;
        if (categoryId != null && categoryId > 0) {
            products = productRepository.findByCategoryId(categoryId);
        } else {
            products = productRepository.findAllWithCategory();
        }
        return products.stream()
                .map(productMapper::toProduct)
                .toList();

    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(productMapper::toProduct)
                .orElse(null);
    }

}
