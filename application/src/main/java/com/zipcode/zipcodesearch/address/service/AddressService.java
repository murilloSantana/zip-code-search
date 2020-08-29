package com.zipcode.zipcodesearch.address.service;

import com.zipcode.zipcodesearch.address.controller.dto.AddressDTO;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    List<AddressDTO> listAll();
    Optional<AddressDTO> findByZipCode(String zipCode);
    Optional<AddressDTO> save(AddressDTO addressDTO);
    Optional<AddressDTO> update(Long addressId, AddressDTO addressDTO);
    void delete(AddressDTO addressDTO);
}
