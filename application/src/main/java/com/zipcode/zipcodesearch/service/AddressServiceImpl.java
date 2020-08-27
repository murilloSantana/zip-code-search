package com.zipcode.zipcodesearch.service;

import com.zipcode.zipcodesearch.controller.address.dto.AddressDTO;
import com.zipcode.zipcodesearch.usecase.address.finder.AddressFinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    private AddressFinder addressFinder;

    public AddressServiceImpl(AddressFinder addressFinder) {
        this.addressFinder = addressFinder;
    }

    @Override
    @Cacheable(cacheNames="addressByZipCode", unless="#result == null" )
    public Optional<AddressDTO> findAddressByZipCode(String zipCode) {
        return addressFinder.findAddressByZipCode(zipCode)
                .map((address) -> AddressDTO.addressToAddressDTO(address))
                .orElse(Optional.empty());
    }
}
