package com.zipcode.zipcodesearch.adapter;

import com.zipcode.zipcodesearch.model.Address;

import java.util.Optional;

public interface AddressRepository {
    Optional<Address> findByZipCode(String zipCode);
}
