package com.zipcode.zipcodesearch.service;

import com.zipcode.zipcodesearch.controller.address.dto.AddressDTO;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    Optional<List<AddressDTO>> listAll();
    Optional<AddressDTO> findByZipCode(String zipCode);
    Optional<AddressDTO> save(AddressDTO addressDTO);
    Optional<AddressDTO> update(AddressDTO addressDTO);
    void delete(AddressDTO addressDTO);
}
