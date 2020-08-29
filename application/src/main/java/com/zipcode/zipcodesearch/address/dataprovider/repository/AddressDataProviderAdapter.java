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
        return this.addressConverter.addressEntityToAddress(this.addressRepository.findAll());
    }

    @Override
    public Optional<Address> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Address> findByZipCode(String zipCode) {
        return this.addressRepository.findByZipCode(zipCode)
                .map((addressEntity) -> this.addressConverter.addressEntityToAddress(addressEntity))
                .orElse(Optional.empty());
    }

    @Override
    public Optional<Address> save(Address address) {
        AddressData addressData = this.addressRepository.save(this.addressConverter.addressToAddressEntity(address));
        return this.addressConverter.addressEntityToAddress(addressData);
    }

}
