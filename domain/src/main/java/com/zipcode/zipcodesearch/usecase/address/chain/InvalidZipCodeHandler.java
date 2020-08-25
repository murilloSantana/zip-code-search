package com.zipcode.zipcodesearch.usecase.address.chain;

import com.zipcode.zipcodesearch.entity.Address;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InvalidZipCodeHandler implements AddressSearchChain {

    private AddressSearchChain addressSearchChain;

    @Override
    public void setNextHandler(AddressSearchChain nextHandler) {
        this.addressSearchChain = nextHandler;
    }

    @Override
    public Address check(String zipCode) {

        if(this.isInvalid(zipCode)) throw new InvalidZipCodeException("CEP inválido");

        return Optional
                .ofNullable(this.addressSearchChain)
                .map((addressSearchChain) -> addressSearchChain.check(zipCode))
                .orElse(null);
    }

    private boolean isInvalid(String zipCode) {
        Pattern zipCodeExpectedPattern = Pattern.compile("[0-9]{5}-[0-9]{3}");
        Matcher zipCodeMatcher = zipCodeExpectedPattern.matcher(zipCode);
        return !zipCodeMatcher.find();
    }
}
