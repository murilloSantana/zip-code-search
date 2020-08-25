package com.zipcode.zipcodesearch.usecase.address.chain;

import com.zipcode.zipcodesearch.entity.Address;

public class NotFoundZipCodeHandler implements AddressSearchChain{

    private AddressSearchChain addressSearchChain;

    @Override
    public void setNextHandler(AddressSearchChain nextHandler) {
        this.addressSearchChain = nextHandler;
    }

    @Override
    public Address check(String zipCode) {
        return null;
    }
}
