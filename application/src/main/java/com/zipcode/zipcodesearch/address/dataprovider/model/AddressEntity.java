package com.zipcode.zipcodesearch.address.dataprovider.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "zip_code", unique = true)
    private String zipCode;
    private String street;
    private String district;
    private String city;
    private String state;

}
