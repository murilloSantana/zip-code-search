package com.zipcode.zipcodesearch.usecase.address.chain;


import com.zipcode.zipcodesearch.entity.Address;
import com.zipcode.zipcodesearch.entity.InvalidZipCodeException;
import com.zipcode.zipcodesearch.usecase.address.validator.ZipCodeNumberValidator;
import com.zipcode.zipcodesearch.usecase.address.validator.ZipCodeSizeValidator;
import com.zipcode.zipcodesearch.usecase.address.validator.ZipCodeValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class InvalidZipCodeHandler implements AddressSearchChain {

    private AddressSearchChain addressSearchChain;

    private static final Logger log = LoggerFactory.getLogger(InvalidZipCodeHandler.class);

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
