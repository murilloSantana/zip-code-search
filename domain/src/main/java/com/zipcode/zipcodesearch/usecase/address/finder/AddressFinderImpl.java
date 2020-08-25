package com.zipcode.zipcodesearch.usecase.address.finder;

import com.zipcode.zipcodesearch.entity.Address;
import com.zipcode.zipcodesearch.usecase.address.chain.InvalidZipCodeHandler;
import com.zipcode.zipcodesearch.usecase.address.chain.AddressSearchChain;
import com.zipcode.zipcodesearch.usecase.address.chain.ValidZipCodeHandler;

public class AddressFinderImpl implements AddressFinder {

    @Override
    public Address findAddressByZipCode(String zipCode) {
        AddressSearchChain invalidZipCodeChain = new InvalidZipCodeHandler();
        invalidZipCodeChain.setNextHandler(new ValidZipCodeHandler());

        return invalidZipCodeChain.check(zipCode);
    }
}
