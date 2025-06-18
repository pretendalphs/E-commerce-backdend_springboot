package com.one.eCommerce_backend.repositories;

import com.one.eCommerce_backend.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}