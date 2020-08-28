package com.zipcode.zipcodesearch.usecase.address.dataprovider.adapter;

import com.zipcode.zipcodesearch.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressDataProvider {
    List<Address> listAll();
    Optional<Address> findByZipCode(String zipCode);
    Optional<Address> saveAddress(Address address);
}
