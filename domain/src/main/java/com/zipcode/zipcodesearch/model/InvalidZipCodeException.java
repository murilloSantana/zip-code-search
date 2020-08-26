package com.zipcode.zipcodesearch.model;

public class InvalidZipCodeException extends RuntimeException {
    public InvalidZipCodeException(String message) {
        super(message);
    }
}
