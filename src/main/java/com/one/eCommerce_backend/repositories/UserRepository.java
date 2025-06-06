package com.one.eCommerce_backend.repositories;

import com.one.eCommerce_backend.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
