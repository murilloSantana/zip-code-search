package com.zipcode.zipcodesearch.dataprovider.model;

import com.zipcode.zipcodesearch.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String zipCode;
    private String street;
    private String district;
    private String city;
    private String state;

    public static Address addressEntityToAddress(AddressEntity addressEntity) {
        return Address
                .builder()
                .state(addressEntity.getState())
                .city(addressEntity.getCity())
                .district(addressEntity.getDistrict())
                .street(addressEntity.getStreet())
                .zipCode(addressEntity.getZipCode())
                .build();
    }
}
