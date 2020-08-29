package com.zipcode.zipcodesearch.address.dataprovider.repository;


import com.zipcode.zipcodesearch.address.dataprovider.model.AddressData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressData, Long> {
    Optional<AddressData> findByZipCode(String zipCode);
}
