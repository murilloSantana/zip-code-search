package com.zipcode.zipcodesearch.address.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {

    @NotNull
    @NotEmpty
    private String zipCode;
    @NotNull
    @NotEmpty
    private String street;
    @NotNull
    @NotEmpty
    private String district;
    @NotNull
    @NotEmpty
    private String city;
    @NotNull
    @NotEmpty
    private String state;

}
