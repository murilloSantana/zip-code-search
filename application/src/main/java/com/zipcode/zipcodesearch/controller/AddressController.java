package com.zipcode.zipcodesearch.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/address")
public class AddressController {

    @GetMapping("/{zipCode}")
    public ResponseEntity findByZipCode(@PathVariable("zipCode") String zipCode) {
        System.out.println(zipCode);
        return ResponseEntity.ok().build();
    }
}
