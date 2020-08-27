package com.zipcode.zipcodesearch.usecase.address.chain;

import com.zipcode.zipcodesearch.model.Address;
import com.zipcode.zipcodesearch.usecase.address.dataprovider.adapter.AddressDataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ValidZipCodeHandlerTest {

    @InjectMocks
    private ValidZipCodeHandler validZipCodeHandler;

    @Mock
    private AddressDataProvider addressDataProvider;

    @Mock
    private AddressSearchChain addressSearchChain;

    @Test
    public void testValidZipCodeFoundWithOneSearchs() {
        String zipCode = "25015210";
        String zipCodeExpected = "25015210";

        Address addressExpected = Address
                .builder()
                .zipCode(zipCodeExpected)
                .district("Duque de Caxias")
                .build();

        Address addressMock = Address
                .builder()
                .zipCode(zipCodeExpected)
                .district("Duque de Caxias")
                .build();

        Mockito.when(addressDataProvider.findByZipCode(zipCodeExpected)).thenReturn(Optional.ofNullable(addressMock));

        Optional<Address> addressActual = validZipCodeHandler.check(zipCode);

        Assertions.assertNotNull(addressActual);

        Assertions.assertEquals(addressExpected, addressActual.get());

        Mockito.verify(addressDataProvider, Mockito.times(1)).findByZipCode(Mockito.any());

        Mockito.verify(addressDataProvider, Mockito.times(1)).findByZipCode(zipCode);
    }

    @Test
    public void testValidZipCodeFoundWithTwoSearchs() {
        String zipCode = "25015211";
        String zipCodeExpected = "25015210";

        Address addressExpected = Address
                .builder()
                .zipCode(zipCodeExpected)
                .district("Duque de Caxias")
                .build();

        Address addressMock = Address
                .builder()
                .zipCode(zipCodeExpected)
                .district("Duque de Caxias")
                .build();

        Mockito.when(addressDataProvider.findByZipCode(zipCodeExpected)).thenReturn(Optional.ofNullable(addressMock));

        Optional<Address> addressActual = validZipCodeHandler.check(zipCode);

        Assertions.assertNotNull(addressActual);

        Assertions.assertEquals(addressExpected, addressActual.get());

        Mockito.verify(addressDataProvider, Mockito.times(2)).findByZipCode(Mockito.any());

        Mockito.verify(addressDataProvider, Mockito.times(1)).findByZipCode(zipCode);
    }

    @Test
    public void testValidZipCodeFoundWithThreeSearchs() {
        String zipCode = "25015211";
        String zipCodeExpected = "25015200";

        Address addressExpected = Address
                .builder()
                .zipCode(zipCodeExpected)
                .district("Duque de Caxias")
                .build();

        Address addressMock = Address
                .builder()
                .zipCode(zipCodeExpected)
                .district("Duque de Caxias")
                .build();

        Mockito.when(addressDataProvider.findByZipCode(zipCodeExpected)).thenReturn(Optional.ofNullable(addressMock));

        Optional<Address> addressActual = validZipCodeHandler.check(zipCode);

        Assertions.assertNotNull(addressActual);

        Assertions.assertEquals(addressExpected, addressActual.get());

        Mockito.verify(addressDataProvider, Mockito.times(3)).findByZipCode(Mockito.any());

        Mockito.verify(addressDataProvider, Mockito.times(1)).findByZipCode(zipCode);
    }

    @Test
    public void testValidZipCodeFoundWithFourSearchs() {
        String zipCode = "25015211";
        String zipCodeExpected = "25015000";

        Address addressExpected = Address
                .builder()
                .zipCode(zipCodeExpected)
                .district("Duque de Caxias")
                .build();

        Address addressMock = Address
                .builder()
                .zipCode(zipCodeExpected)
                .district("Duque de Caxias")
                .build();

        Mockito.when(addressDataProvider.findByZipCode(zipCodeExpected)).thenReturn(Optional.ofNullable(addressMock));

        Optional<Address> addressActual = validZipCodeHandler.check(zipCode);

        Assertions.assertNotNull(addressActual);

        Assertions.assertEquals(addressExpected, addressActual.get());

        Mockito.verify(addressDataProvider, Mockito.times(4)).findByZipCode(Mockito.any());

        Mockito.verify(addressDataProvider, Mockito.times(1)).findByZipCode(zipCode);
    }

    @Test
    public void testValidZipCodeFoundWithFiveSearchs() {
        String zipCode = "25015211";
        String zipCodeExpected = "25010000";

        Address addressExpected = Address
                .builder()
                .zipCode(zipCodeExpected)
                .district("Duque de Caxias")
                .build();

        Address addressMock = Address
                .builder()
                .zipCode(zipCodeExpected)
                .district("Duque de Caxias")
                .build();

        Mockito.when(addressDataProvider.findByZipCode(zipCodeExpected)).thenReturn(Optional.ofNullable(addressMock));

        Optional<Address> addressActual = validZipCodeHandler.check(zipCode);

        Assertions.assertNotNull(addressActual);

        Assertions.assertEquals(addressExpected, addressActual.get());

        Mockito.verify(addressDataProvider, Mockito.times(5)).findByZipCode(Mockito.any());

        Mockito.verify(addressDataProvider, Mockito.times(1)).findByZipCode(zipCode);
    }

    @Test
    public void testValidZipCodeFoundWithSixSearchs() {
        String zipCode = "25015211";
        String zipCodeExpected = "25000000";

        Address addressExpected = Address
                .builder()
                .zipCode(zipCodeExpected)
                .district("Duque de Caxias")
                .build();

        Address addressMock = Address
                .builder()
                .zipCode(zipCodeExpected)
                .district("Duque de Caxias")
                .build();

        Mockito.when(addressDataProvider.findByZipCode(zipCodeExpected)).thenReturn(Optional.ofNullable(addressMock));

        Optional<Address> addressActual = validZipCodeHandler.check(zipCode);

        Assertions.assertNotNull(addressActual);

        Assertions.assertEquals(addressExpected, addressActual.get());

        Mockito.verify(addressDataProvider, Mockito.times(6)).findByZipCode(Mockito.any());

        Mockito.verify(addressDataProvider, Mockito.times(1)).findByZipCode(zipCode);
    }

    @Test
    public void testValidZipCodeFoundWithSevenSearchs() {
        String zipCode = "25115211";
        String zipCodeExpected = "25000000";

        Address addressExpected = Address
                .builder()
                .zipCode(zipCodeExpected)
                .district("Duque de Caxias")
                .build();

        Address addressMock = Address
                .builder()
                .zipCode(zipCodeExpected)
                .district("Duque de Caxias")
                .build();

        Mockito.when(addressDataProvider.findByZipCode(zipCodeExpected)).thenReturn(Optional.ofNullable(addressMock));

        Optional<Address> addressActual = validZipCodeHandler.check(zipCode);

        Assertions.assertNotNull(addressActual);

        Assertions.assertEquals(addressExpected, addressActual.get());

        Mockito.verify(addressDataProvider, Mockito.times(7)).findByZipCode(Mockito.any());

        Mockito.verify(addressDataProvider, Mockito.times(1)).findByZipCode(zipCode);
    }

    @Test
    public void testValidZipCodeFoundWithEightSearchs() {
        String zipCode = "25115211";
        String zipCodeExpected = "20000000";

        Address addressExpected = Address
                .builder()
                .zipCode(zipCodeExpected)
                .district("Duque de Caxias")
                .build();

        Address addressMock = Address
                .builder()
                .zipCode(zipCodeExpected)
                .district("Duque de Caxias")
                .build();

        Mockito.when(addressDataProvider.findByZipCode(zipCodeExpected)).thenReturn(Optional.ofNullable(addressMock));

        Optional<Address> addressActual = validZipCodeHandler.check(zipCode);

        Assertions.assertNotNull(addressActual);

        Assertions.assertEquals(addressExpected, addressActual.get());

        Mockito.verify(addressDataProvider, Mockito.times(8)).findByZipCode(Mockito.any());

        Mockito.verify(addressDataProvider, Mockito.times(1)).findByZipCode(zipCode);
    }

    @Test
    public void testValidZipCodeFoundWithNineSearchs() {
        String zipCode = "25115211";
        String zipCodeExpected = "00000000";

        Address addressExpected = Address
                .builder()
                .zipCode(zipCodeExpected)
                .district("Duque de Caxias")
                .build();

        Address addressMock = Address
                .builder()
                .zipCode(zipCodeExpected)
                .district("Duque de Caxias")
                .build();

        Mockito.when(addressDataProvider.findByZipCode(zipCodeExpected)).thenReturn(Optional.ofNullable(addressMock));

        Optional<Address> addressActual = validZipCodeHandler.check(zipCode);

        Assertions.assertNotNull(addressActual);

        Assertions.assertEquals(addressExpected, addressActual.get());

        Mockito.verify(addressDataProvider, Mockito.times(9)).findByZipCode(Mockito.any());

        Mockito.verify(addressDataProvider, Mockito.times(1)).findByZipCode(zipCode);
    }

    @Test
    public void testValidZipCodeNotFound() {
        String zipCode = "12345678";

        Mockito.when(addressDataProvider.findByZipCode(zipCode)).thenReturn(Optional.empty());

        Optional<Address> addressActual = validZipCodeHandler.check(zipCode);

        Assertions.assertFalse(addressActual.isPresent());

        Mockito.verify(addressDataProvider, Mockito.times(9)).findByZipCode(Mockito.any());

        Mockito.verify(addressDataProvider, Mockito.times(1)).findByZipCode("12345678");
        Mockito.verify(addressDataProvider, Mockito.times(1)).findByZipCode("12345670");
        Mockito.verify(addressDataProvider, Mockito.times(1)).findByZipCode("12345600");
        Mockito.verify(addressDataProvider, Mockito.times(1)).findByZipCode("12345000");
        Mockito.verify(addressDataProvider, Mockito.times(1)).findByZipCode("12340000");
        Mockito.verify(addressDataProvider, Mockito.times(1)).findByZipCode("12300000");
        Mockito.verify(addressDataProvider, Mockito.times(1)).findByZipCode("12000000");
        Mockito.verify(addressDataProvider, Mockito.times(1)).findByZipCode("10000000");
        Mockito.verify(addressDataProvider, Mockito.times(1)).findByZipCode("00000000");

    }

    @Test
    public void testValidZipCodeWithNextHandler() {
        String zipCode = "25015212";

        Address addressExpected = Address
                .builder()
                .state("Rio de Janeiro")
                .city("Rio de Janeiro")
                .district("Flamengo")
                .street("Rua Marques de Abrantes")
                .zipCode(zipCode)
                .build();

        Mockito.when(addressDataProvider.findByZipCode(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(addressSearchChain.check(zipCode)).thenReturn(Optional.ofNullable(addressExpected));

        validZipCodeHandler.setNextHandler(addressSearchChain);

        Optional<Address> addressActual = validZipCodeHandler.check(zipCode);

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

        validZipCodeHandler.setNextHandler(addressSearchChain);
        Optional<Address> addressActual = validZipCodeHandler.check(zipCode);

        Assertions.assertEquals(addressExpected, addressActual.get());
    }
}
