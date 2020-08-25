package com.zipcode.zipcodesearch.usecase.address.chain;

import com.zipcode.zipcodesearch.model.Address;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InvalidZipCodeHandlerTest {

    @InjectMocks
    private InvalidZipCodeHandler invalidZipCodeHandler;

    @Mock
    private AddressSearchChain addressSearchChain;

    @Test(expected = InvalidZipCodeException.class)
    public void testInvalidZipCode() {
        String zipCode = "22230060";
        invalidZipCodeHandler.check(zipCode);
    }

    @Test
    public void testValidZipCode() {
        String zipCode = "22230-060";
        invalidZipCodeHandler.check(zipCode);
    }

    @Test
    public void testValidZipCodeWithNextHandler() {
        String zipCode = "22230-060";

        Address addressExpected = Address
                .builder()
                .state("Rio de Janeiro")
                .city("Rio de Janeiro")
                .district("Flamengo")
                .street("Rua Marques de Abrantes")
                .zipCode("22230-060")
                .build();

        Mockito.when(addressSearchChain.check(zipCode)).thenReturn(addressExpected);

        Address addressActual = invalidZipCodeHandler.check(zipCode);

        Assert.assertEquals(addressExpected, addressActual);
    }

    @Test
    public void testValidZipCodeWithRealNextHandler() {
        String zipCode = "22230-060";

        Address addressExpected = Address
                .builder()
                .state("Rio de Janeiro")
                .city("Duque de Caxias")
                .district("PQ. Lafaiete")
                .street("Rua David de Oliveira")
                .zipCode("25015-210")
                .build();

        AddressSearchChain addressSearchChain = new AddressSearchChain() {
            @Override
            public void setNextHandler(AddressSearchChain nextHandler) {

            }

            @Override
            public Address check(String zipCode) {
                return addressExpected;
            }
        };

        invalidZipCodeHandler.setNextHandler(addressSearchChain);
        Address addressActual = invalidZipCodeHandler.check(zipCode);

        Assert.assertEquals(addressExpected, addressActual);
    }
}
