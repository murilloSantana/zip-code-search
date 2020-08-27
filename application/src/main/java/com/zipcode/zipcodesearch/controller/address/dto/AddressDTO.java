package com.zipcode.zipcodesearch.controller.address.dto;

import com.zipcode.zipcodesearch.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {

    private String zipCode;
    private String street;
    private String district;
    private String city;
    private String state;

    public static Optional<AddressDTO> addressToAddressDTO(Address address) {
        AddressDTO addressDTO = AddressDTO
                .builder()
                .state(address.getState())
                .city(address.getCity())
                .district(address.getDistrict())
                .street(address.getStreet())
                .zipCode(address.getZipCode())
                .build();

        return Optional.ofNullable(addressDTO);
    }
}
