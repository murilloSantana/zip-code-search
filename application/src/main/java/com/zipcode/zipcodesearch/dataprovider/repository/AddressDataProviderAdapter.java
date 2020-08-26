package com.zipcode.zipcodesearch.dataprovider.repository;

import com.zipcode.zipcodesearch.adapter.AddressDataProvider;
import com.zipcode.zipcodesearch.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressDataProviderAdapter implements AddressDataProvider {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Optional<Address> findByZipCode(String zipCode) {
        return Optional.empty();
    }
}
