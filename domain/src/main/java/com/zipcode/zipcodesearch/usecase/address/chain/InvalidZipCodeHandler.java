package com.zipcode.zipcodesearch.usecase.address.chain;


import com.zipcode.zipcodesearch.model.Address;
import com.zipcode.zipcodesearch.model.InvalidZipCodeException;
import com.zipcode.zipcodesearch.usecase.address.validator.ZipCodeNumberValidator;
import com.zipcode.zipcodesearch.usecase.address.validator.ZipCodeSizeValidator;
import com.zipcode.zipcodesearch.usecase.address.validator.ZipCodeValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

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
        ZipCodeValidator zipCodeSizeValidator = new ZipCodeSizeValidator();
        ZipCodeValidator zipCodeNumberValidator = new ZipCodeNumberValidator();
        return !zipCodeSizeValidator.isValid(zipCode) || !zipCodeNumberValidator.isValid(zipCode);
    }
}
