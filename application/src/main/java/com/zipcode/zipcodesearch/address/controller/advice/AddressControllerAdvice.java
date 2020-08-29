package com.zipcode.zipcodesearch.address.controller.advice;

import com.zipcode.zipcodesearch.entity.AddressNotFoundException;
import com.zipcode.zipcodesearch.entity.InvalidZipCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AddressControllerAdvice {

    @ExceptionHandler({InvalidZipCodeException.class, AddressNotFoundException.class})
    public ResponseEntity handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGenericException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

}
