package com.zipcode.zipcodesearch.usecase.address.chain;

import com.zipcode.zipcodesearch.entity.Address;
import com.zipcode.zipcodesearch.usecase.address.dataprovider.adapter.AddressDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class ValidZipCodeHandler implements AddressSearchChain {

    private static final Logger log = LoggerFactory.getLogger(ValidZipCodeHandler.class);

    private AddressSearchChain addressSearchChain;
    private final AddressDataProvider addressDataProvider;

    public ValidZipCodeHandler(AddressDataProvider addressDataProvider) {
       this.addressDataProvider = addressDataProvider;
    }

    @Override
    public void setNextHandler(AddressSearchChain nextHandler) {
        this.addressSearchChain = nextHandler;
    }

    @Override
    public Optional<Address> check(String zipCode) {
        int cepSize = 8;
        StringBuilder builder = new StringBuilder(zipCode);

        Optional<Address> address = this.findRecursiveZipCode(builder, cepSize - 1);

        return address.or(() ->
                Optional
                        .ofNullable(this.addressSearchChain)
                        .map((addressSearchChain) -> addressSearchChain.check(zipCode))
                        .orElse(Optional.empty())
        );
    }

    public Optional<Address> findRecursiveZipCode(StringBuilder zipCodeBuilder, int positionToReplace) {
        String zipCode = zipCodeBuilder.toString();
        Optional<Address> address = addressDataProvider.findByZipCode(zipCode);

        if(positionToReplace > -1 && !address.isPresent()) {
            log.info("Valid Address Not Found: ZIP_CODE {}", zipCode);
            zipCodeBuilder.setCharAt(positionToReplace, '0');
            return findRecursiveZipCode(zipCodeBuilder, --positionToReplace);
        }

        return address;
    }

}
