package com.zipcode.zipcodesearch.controller.address.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
