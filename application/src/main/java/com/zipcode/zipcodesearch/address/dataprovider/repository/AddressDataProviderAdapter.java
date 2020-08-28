package com.zipcode.zipcodesearch.address.dataprovider.repository;

import com.zipcode.zipcodesearch.usecase.address.dataprovider.adapter.AddressDataProvider;
import com.zipcode.zipcodesearch.address.dataprovider.model.AddressEntity;
import com.zipcode.zipcodesearch.model.Address;
import com.zipcode.zipcodesearch.address.service.AddressConverter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressDataProviderAdapter implements AddressDataProvider {

    private AddressRepository addressRepository;

    private AddressConverter addressConverter;

    public AddressDataProviderAdapter(AddressRepository addressRepository, AddressConverter addressConverter) {
        this.addressRepository = addressRepository;
        this.addressConverter = addressConverter;
    }

    @Override
    public Optional<Address> findByZipCode(String zipCode) {
        return this.addressRepository.findByZipCode(zipCode)
                .map((addressEntity) -> this.addressConverter.addressEntityToAddress(addressEntity))
                .orElse(Optional.empty());
    }

    @Override
    public Optional<Address> saveAddress(Address address) {
        AddressEntity addressEntity = this.addressRepository.save(this.addressConverter.addressToAddressEntity(address));
        return this.addressConverter.addressEntityToAddress(addressEntity);
    }

}
