package com.zipcode.zipcodesearch.address.service;

import com.zipcode.zipcodesearch.address.controller.dto.AddressDTO;
import com.zipcode.zipcodesearch.entity.Address;
import com.zipcode.zipcodesearch.entity.AddressNotFoundException;
import com.zipcode.zipcodesearch.usecase.address.dataprovider.AddressUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressUseCase addressUseCase;

    private AddressConverter addressConverter;

    private CacheManager cacheManager;

    private static final String  addressCacheName = "addressByZipCode";

    private static final Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);

    public AddressServiceImpl(AddressUseCase addressUseCase, AddressConverter addressConverter, CacheManager cacheManager) {
        this.addressUseCase = addressUseCase;
        this.addressConverter = addressConverter;
        this.cacheManager = cacheManager;
    }

    @Override
    public List<AddressDTO> listAll() {
        return this.addressConverter.addressToAddressDTO(this.addressUseCase.listAll());
    }

    @Override
    @Cacheable(cacheNames = addressCacheName, unless = "#result == null" )
    public Optional<AddressDTO> findByZipCode(String zipCode) {
        log.info("Trying to retrieve the non-cached address ZIP_CODE {}", zipCode);

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
    public Optional<AddressDTO> update(Long addressId, AddressDTO addressDTO) {
        Optional<Address> address = this.addressUseCase.update(addressId, this.addressConverter.addressDTOToAddress(addressDTO));
        return this.addressConverter.addressToAddressDTO(address);
    }

    @Override
    public void delete(Long addressId) {
        Optional<Address> address = this.addressUseCase.findById(addressId);

        if(!address.isPresent()) throw new AddressNotFoundException("Endereço não encontrado");

        this.addressUseCase.delete(addressId);

        cacheManager.getCache(addressCacheName).evictIfPresent(address.get().getZipCode());
    }

    public void setAddressConverter(AddressConverter addressConverter) {
        this.addressConverter = addressConverter;
    }

    public void setAddressUseCase(AddressUseCase addressUseCase) {
        this.addressUseCase = addressUseCase;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
}
