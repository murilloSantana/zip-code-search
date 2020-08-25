package com.zipcode.zipcodesearch.usecase.address.finder;


import com.zipcode.zipcodesearch.model.Address;

public interface AddressFinder {
    Address findAddressByZipCode(String zipCode);
}
