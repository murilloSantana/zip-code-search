package com.zipcode.zipcodesearch.repository;

import com.zipcode.zipcodesearch.entity.AddressEntity;

import java.util.Optional;

public interface AddressRepository {
    Optional<AddressEntity> findByZipCode(String zipCode);
}
