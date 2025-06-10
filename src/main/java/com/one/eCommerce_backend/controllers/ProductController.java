package com.one.eCommerce_backend.controllers;

import com.one.eCommerce_backend.dtos.ProductDto;
import com.one.eCommerce_backend.dtos.RegisterNewProduct;
import com.one.eCommerce_backend.dtos.UpdateProductRequest;
import com.one.eCommerce_backend.entities.Product;
import com.one.eCommerce_backend.mappers.ProductMapper;
import com.one.eCommerce_backend.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

    @PostMapping()
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody RegisterNewProduct request,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        var product = productMapper.createProduct(request);
        productRepository.save(product);
        var productDto = productMapper.toProduct(product);
        var uri = uriComponentsBuilder.path("/products/{id}").buildAndExpand(productDto.getId()).toUri();
        return ResponseEntity.created(uri).body(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateProductRequest request
    ) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productMapper.UpdateProduct(request, product);
        productRepository.save(product);
        return ResponseEntity.ok().body(productMapper.toProduct(product));
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productRepository.delete(product);
        return ResponseEntity.ok().build();
        
    }
}

