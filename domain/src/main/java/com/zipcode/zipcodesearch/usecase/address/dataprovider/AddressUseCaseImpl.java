package com.zipcode.zipcodesearch.usecase.address.dataprovider;

import com.zipcode.zipcodesearch.entity.Address;
import com.zipcode.zipcodesearch.entity.InvalidZipCodeException;
import com.zipcode.zipcodesearch.usecase.address.chain.AddressSearchChain;
import com.zipcode.zipcodesearch.usecase.address.chain.InvalidZipCodeHandler;
import com.zipcode.zipcodesearch.usecase.address.chain.ValidZipCodeHandler;
import com.zipcode.zipcodesearch.usecase.address.dataprovider.adapter.AddressDataProvider;
import com.zipcode.zipcodesearch.usecase.address.validator.ZipCodeNumberValidator;
import com.zipcode.zipcodesearch.usecase.address.validator.ZipCodeSizeValidator;
import com.zipcode.zipcodesearch.usecase.address.validator.ZipCodeValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class AddressUseCaseImpl implements AddressUseCase {

    private final AddressDataProvider addressDataProvider;

    private static final Logger log = LoggerFactory.getLogger(AddressUseCaseImpl.class);

    public AddressUseCaseImpl(AddressDataProvider addressDataProvider) {
        this.addressDataProvider = addressDataProvider;
    }

    @Override
    public List<Address> listAll() {
        return this.addressDataProvider.listAll();
    }

    @Override
    public Optional<Address> findByZipCode(String zipCode) {
        log.info("Searching Zip Code");
        AddressSearchChain invalidZipCodeChain = new InvalidZipCodeHandler();
        invalidZipCodeChain.setNextHandler(new ValidZipCodeHandler(this.addressDataProvider));

        return invalidZipCodeChain.check(zipCode);
    }

    private boolean isInvalid(String zipCode) {
        ZipCodeValidator zipCodeSizeValidator = new ZipCodeSizeValidator();
        ZipCodeValidator zipCodeNumberValidator = new ZipCodeNumberValidator();
        return !zipCodeSizeValidator.isValid(zipCode) || !zipCodeNumberValidator.isValid(zipCode);
    }

    @Override
    public Optional<Address> save(Address address) {
        if(this.isInvalid(address.getZipCode())) throw new InvalidZipCodeException("CPF Inválido");

        return this.addressDataProvider.saveAddress(address);
    }

    @Override
    public Optional<Address> update(Address address) {
        return Optional.empty();
    }

    @Override
    public void delete(Address address) {

    }
}
