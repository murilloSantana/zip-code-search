package com.zipcode.zipcodesearch.entity;

public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException(String message) {
        super(message);
    }
}
