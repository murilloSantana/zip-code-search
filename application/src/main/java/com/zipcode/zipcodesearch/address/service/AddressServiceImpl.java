package com.zipcode.zipcodesearch.address.service;

import com.zipcode.zipcodesearch.address.controller.dto.AddressDTO;
import com.zipcode.zipcodesearch.entity.Address;
import com.zipcode.zipcodesearch.usecase.address.dataprovider.AddressUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    private AddressUseCase addressUseCase;

    private AddressConverter addressConverter;

    public AddressServiceImpl(AddressUseCase addressUseCase, AddressConverter addressConverter) {
        this.addressUseCase = addressUseCase;
        this.addressConverter = addressConverter;
    }

    @Override
    public List<AddressDTO> listAll() {
        return this.addressConverter.addressToAddressDTO(this.addressUseCase.listAll());
    }

    @Override
    @Cacheable(cacheNames="addressByZipCode", unless="#result == null" )
    public Optional<AddressDTO> findByZipCode(String zipCode) {
        return this.addressUseCase.findByZipCode(zipCode)
                .map((address) -> this.addressConverter.addressToAddressDTO(address))
                .orElse(Optional.empty());
    }

    @Override
    public Optional<AddressDTO> save(AddressDTO addressDTO) {
        Optional<Address> address = this.addressUseCase.save(this.addressConverter.addressDTOToAddress(addressDTO));
        return this.addressConverter.addressToAddressDTO(address);
    }

    @Override
    public Optional<AddressDTO> update(AddressDTO addressDTO) {
        return Optional.empty();
    }

    @Override
    public void delete(AddressDTO addressDTO) {

    }

    public void setAddressConverter(AddressConverter addressConverter) {
        this.addressConverter = addressConverter;
    }

    public void setAddressUseCase(AddressUseCase addressUseCase) {
        this.addressUseCase = addressUseCase;
    }

}
