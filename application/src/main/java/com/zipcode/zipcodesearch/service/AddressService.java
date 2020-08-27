package com.zipcode.zipcodesearch.service;

import com.zipcode.zipcodesearch.controller.address.dto.AddressDTO;

import java.util.Optional;

public interface AddressService {
    Optional<AddressDTO> findAddressByZipCode(String zipCode);
}
