package com.zipcode.zipcodesearch.usecase.address.dataprovider;

import com.zipcode.zipcodesearch.usecase.address.dataprovider.adapter.AddressDataProvider;
import com.zipcode.zipcodesearch.model.Address;
import com.zipcode.zipcodesearch.usecase.address.chain.AddressSearchChain;
import com.zipcode.zipcodesearch.usecase.address.chain.InvalidZipCodeHandler;
import com.zipcode.zipcodesearch.usecase.address.chain.ValidZipCodeHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class AddressUseCaseImpl implements AddressUseCase {

    private final AddressDataProvider addressDataProvider;

    public AddressUseCaseImpl(AddressDataProvider addressDataProvider) {
        this.addressDataProvider = addressDataProvider;
    }

    @Override
    public Optional<Address> findAddressByZipCode(String zipCode) {
        log.info("Searching Zip Code");
        AddressSearchChain invalidZipCodeChain = new InvalidZipCodeHandler();
        invalidZipCodeChain.setNextHandler(new ValidZipCodeHandler(this.addressDataProvider));

        return invalidZipCodeChain.check(zipCode);
    }

    @Override
    public Optional<Address> saveAddress(Address address) {
        return this.addressDataProvider.saveAddress(address);
    }

    @Override
    public Optional<Address> updateAddress(Address address) {
        return Optional.empty();
    }

    @Override
    public void deleteAddress(Address address) {

    }
}
