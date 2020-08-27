package com.zipcode.zipcodesearch.usecase.address.dataprovider;


import com.zipcode.zipcodesearch.model.Address;

import java.util.Optional;

public interface AddressUseCase {
    Optional<Address> findByZipCode(String zipCode);
    Optional<Address> save(Address address);
    Optional<Address> update(Address address);
    void delete(Address address);
}
