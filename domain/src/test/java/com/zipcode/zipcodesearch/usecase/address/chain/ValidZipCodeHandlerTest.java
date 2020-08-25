package com.zipcode.zipcodesearch.usecase.address.chain;

import com.zipcode.zipcodesearch.model.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ValidZipCodeHandlerTest {

    @InjectMocks
    private ValidZipCodeHandler validZipCodeHandler;

    @Test
    public void testValidZipCodeFound() {
        String zipCode = "22230061";

        Address result = validZipCodeHandler.check(zipCode);

        System.out.println(result);
    }
}
