package com.zipcode.zipcodesearch.usecase.address.chain;

import com.zipcode.zipcodesearch.entity.Address;

public class InvalidZipCodeHandler implements AddressSearchChain {

    private AddressSearchChain addressSearchChain;

    @Override
    public void setNextHandler(AddressSearchChain nextHandler) {
        this.addressSearchChain = nextHandler;
    }

    @Override
    public Address check(String zipCode) {
        if(zipCode.length() < 5) throw new ZipCodeException("CEP inválido");

        return Address
                .builder()
                .zipCode(zipCode)
                .city("Rio de Janeiro")
                .build();
    }
}
