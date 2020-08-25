package com.zipcode.zipcodesearch.usecase.address.finder;

import com.zipcode.zipcodesearch.model.Address;
import com.zipcode.zipcodesearch.usecase.address.chain.InvalidZipCodeException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AddressFinderImplTest {
    @InjectMocks
    private AddressFinderImpl addressFinderImpl;

    @Test(expected = InvalidZipCodeException.class)
    public void testSearchInvalidZipCode() {
        addressFinderImpl.findAddressByZipCode("2223006");
    }

    @Test
    public void testSearchValidZipCode() {
        Address result = addressFinderImpl.findAddressByZipCode("22230061");
    }
}