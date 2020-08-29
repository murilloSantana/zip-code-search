package com.zipcode.zipcodesearch.usecase.address.chain;

import com.zipcode.zipcodesearch.entity.Address;
import com.zipcode.zipcodesearch.entity.InvalidZipCodeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InvalidZipCodeHandlerTest {

    @InjectMocks
    private InvalidZipCodeHandler invalidZipCodeHandler;

    @Mock
    private AddressSearchChain addressSearchChain;

    @Test
    public void testInvalidZipCodeSize() {
        assertThrows(InvalidZipCodeException.class, () -> {
            String zipCode = "2223006";
            invalidZipCodeHandler.check(zipCode);
        });
    }

    @Test
    public void testInvalidZipCodeNumber() {
        assertThrows(InvalidZipCodeException.class, () -> {
            String zipCode = "2223006m";
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

        Address addressExpected = new Address("Rio de Janeiro",
                "Rio de Janeiro", "Flamengo", "Rua Marques de Abrantes", "22230060");

        when(addressSearchChain.check(zipCode)).thenReturn(Optional.ofNullable(addressExpected));

        Optional<Address> addressActual = invalidZipCodeHandler.check(zipCode);

        assertEquals(addressExpected, addressActual.get());
    }

    @Test
    public void testValidZipCodeWithRealNextHandler() {
        String zipCode = "22230060";

        Address addressExpected = new Address("Rio de Janeiro",
                "Duque de Caxias", "PQ. Lafaiete", "Rua David de Oliveira", "25015210");

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

        assertEquals(addressExpected, addressActual.get());
    }
}
