package com.zipcode.zipcodesearch.repository;


import com.zipcode.zipcodesearch.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    Optional<AddressEntity> findByZipCode(String zipCode);
}
