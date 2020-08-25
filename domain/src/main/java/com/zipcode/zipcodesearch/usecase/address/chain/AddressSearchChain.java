package com.zipcode.zipcodesearch.usecase.address.chain;

import com.zipcode.zipcodesearch.entity.Address;

public interface AddressSearchChain {
    void setNextHandler(AddressSearchChain nextHandler);
    Address check(String zipCode);
}
