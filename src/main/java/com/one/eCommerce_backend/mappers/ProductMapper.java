package com.one.eCommerce_backend.mappers;

import com.one.eCommerce_backend.dtos.ProductDto;
import com.one.eCommerce_backend.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "category_id")
    ProductDto toProduct(Product product);

    Product toEntity(ProductDto productDto);

    @Mapping(target = "id", ignore = true)
    void UpdateProduct(ProductDto request, @MappingTarget Product product);
}
