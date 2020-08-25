package com.zipcode.zipcodesearch.repository;

import com.zipcode.zipcodesearch.entity.AddressEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AddressRepositoryImpl implements AddressRepository {

    private Map<String, AddressEntity> mockDatabase;
    public AddressRepositoryImpl() {
        mockDatabase = new HashMap<>();
        mockDatabase.put("22230060", new AddressEntity("22230060", "Rio de Janeiro"));
        mockDatabase.put("25015210", new AddressEntity("25015210", "Duque de Caxias"));
    }

    @Override
    public Optional<AddressEntity> findByZipCode(String zipCode) {
        return Optional.ofNullable(this.mockDatabase.get(zipCode));
    }
}
