package com.zipcode.zipcodesearch.entity;

import lombok.Data;

@Data
public class Address {
    private String zipCode;
    private String street;
    private String district;
    private String city;
    private String state;
}
