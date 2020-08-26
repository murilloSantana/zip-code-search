package com.zipcode.zipcodesearch.dataprovider.repository;

import com.zipcode.zipcodesearch.adapter.AddressDataProvider;
import com.zipcode.zipcodesearch.dataprovider.model.AddressEntity;
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
        return addressRepository.findByZipCode(zipCode)
                .map((addressEntity) -> this.addressEntityToAddress(addressEntity));
    }

    private Address addressEntityToAddress(AddressEntity addressEntity) {
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
