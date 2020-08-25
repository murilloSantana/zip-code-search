package com.zipcode.zipcodesearch.usecase.address.finder;

import com.zipcode.zipcodesearch.entity.Address;
import com.zipcode.zipcodesearch.usecase.address.chain.InvalidZipCodeHandler;
import com.zipcode.zipcodesearch.usecase.address.chain.AddressSearchChain;

public class AddressFinderImpl implements AddressFinder {

    @Override
    public Address findAddressByZipCode(String zipCode) {
        AddressSearchChain invalidZipCodeChain = new InvalidZipCodeHandler();

        return invalidZipCodeChain.check(zipCode);
    }
}
