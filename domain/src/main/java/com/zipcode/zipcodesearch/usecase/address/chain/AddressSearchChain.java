package com.zipcode.zipcodesearch.usecase.address.chain;


import com.zipcode.zipcodesearch.entity.Address;

import java.util.Optional;

public interface AddressSearchChain {
    void setNextHandler(AddressSearchChain nextHandler);
    Optional<Address> check(String zipCode);
}
