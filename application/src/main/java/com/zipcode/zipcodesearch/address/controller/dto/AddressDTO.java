package com.zipcode.zipcodesearch.address.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class AddressDTO {

    private String state;
    private String city;
    private String district;
    private String street;
    private String zipCode;

    public AddressDTO(@NotNull @NotEmpty String state, @NotNull @NotEmpty String city,
                      @NotNull @NotEmpty String district, @NotNull @NotEmpty String street, @NotNull @NotEmpty String zipCode) {
        this.state = state;
        this.city = city;
        this.district = district;
        this.street = street;
        this.zipCode = zipCode;
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
        AddressDTO that = (AddressDTO) o;
        return state.equals(that.state) &&
                city.equals(that.city) &&
                district.equals(that.district) &&
                street.equals(that.street) &&
                zipCode.equals(that.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, city, district, street, zipCode);
    }
}
