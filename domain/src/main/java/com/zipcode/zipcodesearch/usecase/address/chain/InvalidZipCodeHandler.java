package com.zipcode.zipcodesearch.usecase.address.chain;


import com.zipcode.zipcodesearch.model.Address;
import com.zipcode.zipcodesearch.model.InvalidZipCodeException;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class InvalidZipCodeHandler implements AddressSearchChain {

    private AddressSearchChain addressSearchChain;

    @Override
    public void setNextHandler(AddressSearchChain nextHandler) {
        this.addressSearchChain = nextHandler;
    }

    @Override
    public Optional<Address> check(String zipCode) {

        if(this.isInvalid(zipCode)) throw new InvalidZipCodeException("CPF Inválido");

        log.info("Zip Code is Valid: ZIP_CODE {}", zipCode);

        return Optional
                .ofNullable(this.addressSearchChain)
                .map((addressSearchChain) -> addressSearchChain.check(zipCode))
                .orElse(Optional.empty());
    }

    private boolean isInvalid(String zipCode) {
        Pattern zipCodeExpectedPattern = Pattern.compile("[0-9]{8}");
        Matcher zipCodeMatcher = zipCodeExpectedPattern.matcher(zipCode);
        return !zipCodeMatcher.find();
    }
}
