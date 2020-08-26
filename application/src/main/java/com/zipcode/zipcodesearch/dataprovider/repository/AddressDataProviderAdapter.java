package com.zipcode.zipcodesearch.dataprovider.repository;

import com.zipcode.zipcodesearch.adapter.AddressDataProvider;
import com.zipcode.zipcodesearch.dataprovider.model.AddressEntity;
import com.zipcode.zipcodesearch.model.Address;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressDataProviderAdapter implements AddressDataProvider {

    private AddressRepository addressRepository;

    public AddressDataProviderAdapter(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Optional<Address> findByZipCode(String zipCode) {
        return addressRepository.findByZipCode(zipCode)
                .map((addressEntity) -> AddressEntity.addressEntityToAddress(addressEntity));
    }

}
