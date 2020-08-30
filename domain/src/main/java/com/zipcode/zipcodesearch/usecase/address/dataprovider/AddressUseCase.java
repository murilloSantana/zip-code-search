package com.zipcode.zipcodesearch.usecase.address.dataprovider;


import com.zipcode.zipcodesearch.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressUseCase {
    List<Address> listAll();
    Optional<Address> findByZipCode(String zipCode);
    Optional<Address> save(Address address);
    Optional<Address> update(Long addressId, Address address);
    void delete(Long addressId);
    Optional<Address> findById(Long addressId);
}
