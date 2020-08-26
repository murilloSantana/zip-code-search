package com.zipcode.zipcodesearch.usecase.address.finder;

import com.zipcode.zipcodesearch.adapter.AddressDataProvider;
import com.zipcode.zipcodesearch.model.Address;
import com.zipcode.zipcodesearch.usecase.address.chain.InvalidZipCodeHandler;
import com.zipcode.zipcodesearch.usecase.address.chain.AddressSearchChain;
import com.zipcode.zipcodesearch.usecase.address.chain.ValidZipCodeHandler;

public class AddressFinderImpl implements AddressFinder {

    private final AddressDataProvider addressDataProvider;

    public AddressFinderImpl(AddressDataProvider addressDataProvider) {
        this.addressDataProvider = addressDataProvider;
    }

    @Override
    public Address findAddressByZipCode(String zipCode) {
        AddressSearchChain invalidZipCodeChain = new InvalidZipCodeHandler();
        invalidZipCodeChain.setNextHandler(new ValidZipCodeHandler(this.addressDataProvider));

        return invalidZipCodeChain.check(zipCode);
    }
}
