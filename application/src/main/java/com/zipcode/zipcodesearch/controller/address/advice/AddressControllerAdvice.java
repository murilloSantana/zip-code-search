package com.zipcode.zipcodesearch.controller.address.advice;

import com.zipcode.zipcodesearch.model.InvalidZipCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AddressControllerAdvice {

    @ExceptionHandler(InvalidZipCodeException.class)
    public ResponseEntity handleInvalidZipCodeException(InvalidZipCodeException invalidZipCodeException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidZipCodeException.getMessage());
    }

}
