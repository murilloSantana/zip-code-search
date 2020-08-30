package com.zipcode.zipcodesearch.entity;

import java.util.Objects;

public class Address {
    private Long id;
    private String state;
    private String city;
    private String district;
    private String street;
    private String zipCode;

    public Address(String state, String city, String district, String street, String zipCode) {
        this.state = state;
        this.city = city;
        this.district = district;
        this.street = street;
        this.zipCode = zipCode;
    }

    public Address(Long id, String state, String city, String district, String street, String zipCode) {
        this.id = id;
        this.state = state;
        this.city = city;
        this.district = district;
        this.street = street;
        this.zipCode = zipCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) &&
                state.equals(address.state) &&
                city.equals(address.city) &&
                district.equals(address.district) &&
                street.equals(address.street) &&
                zipCode.equals(address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state, city, district, street, zipCode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
