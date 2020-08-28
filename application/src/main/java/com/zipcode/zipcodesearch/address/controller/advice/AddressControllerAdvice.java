package com.zipcode.zipcodesearch.address.controller.advice;

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

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGenericException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

}
