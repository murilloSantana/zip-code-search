package com.zipcode.zipcodesearch.repository;

import com.zipcode.zipcodesearch.adapter.AddressRepository;
import com.zipcode.zipcodesearch.model.Address;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AddressRepositoryImpl implements AddressRepository {

    private Map<String, Address> mockDatabase;
    public AddressRepositoryImpl() {
        mockDatabase = new HashMap<>();
        mockDatabase.put("22230060", new Address("22230060", "Rio de Janeiro", "", "", ""));
        mockDatabase.put("25015210", new Address("25015210", "Duque de Caxias", "", "", ""));
    }

    @Override
    public Optional<Address> findByZipCode(String zipCode) {
        return Optional.ofNullable(this.mockDatabase.get(zipCode));
    }
}
