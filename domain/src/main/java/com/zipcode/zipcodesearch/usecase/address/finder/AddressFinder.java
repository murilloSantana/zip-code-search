package com.zipcode.zipcodesearch.usecase.address.finder;


import com.zipcode.zipcodesearch.model.Address;

import java.util.Optional;

public interface AddressFinder {
    Optional<Address> findAddressByZipCode(String zipCode);
}
