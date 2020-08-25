package com.zipcode.zipcodesearch.usecase.address.chain;

public class InvalidZipCodeException extends RuntimeException {
    public InvalidZipCodeException(String message) {
        super(message);
    }
}
