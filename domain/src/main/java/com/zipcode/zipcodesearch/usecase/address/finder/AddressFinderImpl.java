package com.zipcode.zipcodesearch.usecase.address.finder;

import com.zipcode.zipcodesearch.adapter.AddressRepository;
import com.zipcode.zipcodesearch.model.Address;
import com.zipcode.zipcodesearch.usecase.address.chain.InvalidZipCodeHandler;
import com.zipcode.zipcodesearch.usecase.address.chain.AddressSearchChain;
import com.zipcode.zipcodesearch.usecase.address.chain.ValidZipCodeHandler;

public class AddressFinderImpl implements AddressFinder {

    private final AddressRepository addressRepository;

    public AddressFinderImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address findAddressByZipCode(String zipCode) {
        AddressSearchChain invalidZipCodeChain = new InvalidZipCodeHandler();
        invalidZipCodeChain.setNextHandler(new ValidZipCodeHandler(this.addressRepository));

        return invalidZipCodeChain.check(zipCode);
    }
}
