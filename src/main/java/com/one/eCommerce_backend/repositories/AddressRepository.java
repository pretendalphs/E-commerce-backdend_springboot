package com.one.eCommerce_backend.repositories;

import com.one.eCommerce_backend.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}