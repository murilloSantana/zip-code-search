package com.zipcode.zipcodesearch.usecase.address.finder;


import com.zipcode.zipcodesearch.model.Address;

import java.util.Optional;

public interface AddressUseCase {
    Optional<Address> findAddressByZipCode(String zipCode);
    Optional<Address> saveAddress(Address address);
    Optional<Address> updateAddress(Address address);
    void deleteAddress(Address address);
}
