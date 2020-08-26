package com.zipcode.zipcodesearch.dataprovider.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AddressEntity {

    @Id
    private Long id;
    private String zipCode;
    private String street;
    private String district;
    private String city;
    private String state;

    public AddressEntity(String zipCode, String district) {
        this.zipCode = zipCode;
        this.district = district;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
