package com.one.eCommerce_backend.repositories;

import com.one.eCommerce_backend.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}