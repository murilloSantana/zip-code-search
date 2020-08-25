package com.zipcode.zipcodesearch.usecase.address.finder;

import com.zipcode.zipcodesearch.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AddressFinderImplTest {
    @InjectMocks
    private AddressFinderImpl addressFinderImpl;

    @Test
    public void testSearchInvalidZipCode() {
        Address result = addressFinderImpl.findAddressByZipCode("22230064");
        System.out.println(result);
    }
}