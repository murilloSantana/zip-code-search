package com.zipcode.zipcodesearch.usecase.address.chain;

import com.zipcode.zipcodesearch.usecase.address.dataprovider.adapter.AddressDataProvider;
import com.zipcode.zipcodesearch.model.Address;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class ValidZipCodeHandler implements AddressSearchChain {

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
