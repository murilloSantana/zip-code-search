package com.zipcode.zipcodesearch.usecase.address.chain;

import com.zipcode.zipcodesearch.model.Address;
import com.zipcode.zipcodesearch.model.InvalidZipCodeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class InvalidZipCodeHandlerTest {

    @InjectMocks
    private InvalidZipCodeHandler invalidZipCodeHandler;

    @Mock
    private AddressSearchChain addressSearchChain;

    @Test
    public void testInvalidZipCode() {
        Assertions.assertThrows(InvalidZipCodeException.class, () -> {
            String zipCode = "2223006";
            invalidZipCodeHandler.check(zipCode);
        });
    }

    @Test
    public void testValidZipCode() {
        String zipCode = "22230060";
        invalidZipCodeHandler.check(zipCode);
    }

    @Test
    public void testValidZipCodeWithNextHandler() {
        String zipCode = "22230060";

        Address addressExpected = Address
                .builder()
                .state("Rio de Janeiro")
                .city("Rio de Janeiro")
                .district("Flamengo")
                .street("Rua Marques de Abrantes")
                .zipCode("22230060")
                .build();

        Mockito.when(addressSearchChain.check(zipCode)).thenReturn(Optional.ofNullable(addressExpected));

        Optional<Address> addressActual = invalidZipCodeHandler.check(zipCode);

        Assertions.assertEquals(addressExpected, addressActual.get());
    }

    @Test
    public void testValidZipCodeWithRealNextHandler() {
        String zipCode = "22230060";

        Address addressExpected = Address
                .builder()
                .state("Rio de Janeiro")
                .city("Duque de Caxias")
                .district("PQ. Lafaiete")
                .street("Rua David de Oliveira")
                .zipCode("25015210")
                .build();

        AddressSearchChain addressSearchChain = new AddressSearchChain() {
            @Override
            public void setNextHandler(AddressSearchChain nextHandler) {

            }

            @Override
            public Optional<Address> check(String zipCode) {
                return Optional.ofNullable(addressExpected);
            }
        };

        invalidZipCodeHandler.setNextHandler(addressSearchChain);
        Optional<Address> addressActual = invalidZipCodeHandler.check(zipCode);

        Assertions.assertEquals(addressExpected, addressActual.get());
    }
}
