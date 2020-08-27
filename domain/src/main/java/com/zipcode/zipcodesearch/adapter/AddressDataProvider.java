package com.zipcode.zipcodesearch.adapter;

import com.zipcode.zipcodesearch.model.Address;

import java.util.Optional;

public interface AddressDataProvider {
    Optional<Address> findByZipCode(String zipCode);
    Optional<Address> saveAddress(Address address);
}
