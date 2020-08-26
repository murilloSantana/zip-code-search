package com.zipcode.zipcodesearch.usecase.address.chain;

import com.zipcode.zipcodesearch.adapter.AddressRepository;
import com.zipcode.zipcodesearch.model.Address;

import java.util.Optional;

public class ValidZipCodeHandler implements AddressSearchChain {

    private AddressSearchChain addressSearchChain;
    private final AddressRepository addressRepository;

    public ValidZipCodeHandler(AddressRepository addressRepository) {
       this.addressRepository = addressRepository;
    }

    @Override
    public void setNextHandler(AddressSearchChain nextHandler) {
        this.addressSearchChain = nextHandler;
    }

    @Override
    public Address check(String zipCode) {
        int cepSize = 8;
        StringBuilder builder = new StringBuilder(zipCode);

        Address address = this.findRecursiveZipCode(builder, cepSize - 1)
                .orElse(null);

        return Optional
                .ofNullable(this.addressSearchChain)
                .map((addressSearchChain) -> addressSearchChain.check(zipCode))
                .orElse(address);
    }

    public Optional<Address> findRecursiveZipCode(StringBuilder zipCodeBuilder, int positionToReplace) {
        String zipCode = zipCodeBuilder.toString();
        Optional<Address> address = addressRepository.findByZipCode(zipCode);

        if(positionToReplace > -1 && !address.isPresent()) {
            zipCodeBuilder.setCharAt(positionToReplace, '0');
            return findRecursiveZipCode(zipCodeBuilder, --positionToReplace);
        }

        return address;
    }

}
