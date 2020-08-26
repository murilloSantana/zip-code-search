package com.zipcode.zipcodesearch.dataprovider.repository;


import com.zipcode.zipcodesearch.dataprovider.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    Optional<AddressEntity> findByZipCode(String zipCode);
}