package com.zipcode.zipcodesearch.address.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class AddressDTO {

    @NotNull(message = "State cannot be null")
    @NotEmpty(message = "State cannot be empty")
    private String state;
    @NotNull(message = "City cannot be null")
    @NotEmpty(message = "City cannot be empty")
    private String city;
    @NotNull(message = "District cannot be null")
    @NotEmpty(message = "District cannot be empty")
    private String district;
    @NotNull(message = "Street cannot be null")
    @NotEmpty(message = "Street cannot be empty")
    private String street;
    @NotNull(message = "Zip Code cannot be null")
    @NotEmpty(message = "Zip Code cannot be empty")
    private String zipCode;

    public AddressDTO() {}

    public AddressDTO(String state, String city,
                      String district, String street, String zipCode) {
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
