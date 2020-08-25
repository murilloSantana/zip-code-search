package com.zipcode.zipcodesearch.usecase.address.chain;

import com.zipcode.zipcodesearch.entity.AddressEntity;
import com.zipcode.zipcodesearch.model.Address;
import com.zipcode.zipcodesearch.repository.AddressRepository;
import com.zipcode.zipcodesearch.repository.AddressRepositoryImpl;

import java.util.Optional;

public class ValidZipCodeHandler implements AddressSearchChain {

    private AddressSearchChain addressSearchChain;
    private AddressRepository addressRepository;

    public ValidZipCodeHandler() {
        this.addressRepository = new AddressRepositoryImpl();
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
                .map((addressEntity) -> parseAddressEntityToAddress(addressEntity))
                .orElse(null);

        return Optional
                .ofNullable(this.addressSearchChain)
                .map((addressSearchChain) -> addressSearchChain.check(zipCode))
                .orElse(address);
    }

    public Optional<AddressEntity> findRecursiveZipCode(StringBuilder zipCodeBuilder, int positionToReplace) {
        String zipCode = zipCodeBuilder.toString();
        Optional<AddressEntity> addressEntity = addressRepository.findByZipCode(zipCode);

        if(positionToReplace > -1 && !addressEntity.isPresent()) {
            zipCodeBuilder.setCharAt(positionToReplace, '0');
            return findRecursiveZipCode(zipCodeBuilder, --positionToReplace);
        }

        return addressEntity;
    }

    public Address parseAddressEntityToAddress(AddressEntity addressEntity) {
        return Address
                .builder()
                .state(addressEntity.getState())
                .city(addressEntity.getCity())
                .district(addressEntity.getDistrict())
                .street(addressEntity.getStreet())
                .zipCode(addressEntity.getZipCode())
                .build();
    }
}
