package com.zipcode.zipcodesearch.service;

import com.zipcode.zipcodesearch.controller.address.dto.AddressDTO;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    Optional<List<AddressDTO>> listAllAddress();
    Optional<AddressDTO> findAddressByZipCode(String zipCode);
    Optional<AddressDTO> saveAddress(AddressDTO addressDTO);
    Optional<AddressDTO> updateAddress(AddressDTO addressDTO);
    void deleteAddress(AddressDTO addressDTO);
}
