package com.zipcode.zipcodesearch.controller;

import com.zipcode.zipcodesearch.usecase.address.finder.AddressFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressFinder addressFinder;

    @GetMapping(path = "/{zipCode}")
    public ResponseEntity findByZipCode(@PathVariable("zipCode") String zipCode) {
        return addressFinder.findAddressByZipCode(zipCode)
                .map((address) -> ResponseEntity.ok(address))
                .orElse(ResponseEntity.notFound().build());
    }
}
