package com.zipcode.zipcodesearch.address.dataprovider.repository;

import com.zipcode.zipcodesearch.usecase.address.dataprovider.adapter.AddressDataProvider;
import com.zipcode.zipcodesearch.address.dataprovider.model.AddressData;
import com.zipcode.zipcodesearch.entity.Address;
import com.zipcode.zipcodesearch.address.service.AddressConverter;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<Address> listAll() {
        return this.addressConverter.addressDataToAddress(this.addressRepository.findAll());
    }

    @Override
    public boolean existsById(Long id) {
        return this.addressRepository.existsById(id);
    }

    @Override
    public Optional<Address> findByZipCode(String zipCode) {
        return this.addressRepository.findByZipCode(zipCode)
                .map((addressData) -> this.addressConverter.addressDataToAddress(addressData))
                .orElse(Optional.empty());
    }

    @Override
    public Optional<Address> save(Address address) {
        AddressData addressData = this.addressRepository.save(this.addressConverter.addressToAddressData(address));
        return this.addressConverter.addressDataToAddress(addressData);
    }

}
