package com.zipcode.zipcodesearch.usecase.address.chain;

import com.zipcode.zipcodesearch.adapter.AddressRepository;
import com.zipcode.zipcodesearch.model.Address;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ValidZipCodeHandlerTest {

    @InjectMocks
    private ValidZipCodeHandler validZipCodeHandler;

    @Mock
    private AddressRepository addressRepository;

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
                .build();;

        Mockito.when(addressRepository.findByZipCode(zipCodeExpected)).thenReturn(Optional.ofNullable(addressMock));

        Address addressActual = validZipCodeHandler.check(zipCode);

        Assert.assertNotNull(addressActual);

        Assert.assertEquals(addressExpected, addressActual);

        Mockito.verify(addressRepository, Mockito.times(1)).findByZipCode(Mockito.any());

        Mockito.verify(addressRepository, Mockito.times(1)).findByZipCode(zipCode);
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

        Mockito.when(addressRepository.findByZipCode(zipCodeExpected)).thenReturn(Optional.ofNullable(addressMock));

        Address addressActual = validZipCodeHandler.check(zipCode);

        Assert.assertNotNull(addressActual);

        Assert.assertEquals(addressExpected, addressActual);

        Mockito.verify(addressRepository, Mockito.times(2)).findByZipCode(Mockito.any());

        Mockito.verify(addressRepository, Mockito.times(1)).findByZipCode(zipCode);
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

        Mockito.when(addressRepository.findByZipCode(zipCodeExpected)).thenReturn(Optional.ofNullable(addressMock));

        Address addressActual = validZipCodeHandler.check(zipCode);

        Assert.assertNotNull(addressActual);

        Assert.assertEquals(addressExpected, addressActual);

        Mockito.verify(addressRepository, Mockito.times(3)).findByZipCode(Mockito.any());

        Mockito.verify(addressRepository, Mockito.times(1)).findByZipCode(zipCode);
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

        Mockito.when(addressRepository.findByZipCode(zipCodeExpected)).thenReturn(Optional.ofNullable(addressMock));

        Address addressActual = validZipCodeHandler.check(zipCode);

        Assert.assertNotNull(addressActual);

        Assert.assertEquals(addressExpected, addressActual);

        Mockito.verify(addressRepository, Mockito.times(4)).findByZipCode(Mockito.any());

        Mockito.verify(addressRepository, Mockito.times(1)).findByZipCode(zipCode);
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

        Mockito.when(addressRepository.findByZipCode(zipCodeExpected)).thenReturn(Optional.ofNullable(addressMock));

        Address addressActual = validZipCodeHandler.check(zipCode);

        Assert.assertNotNull(addressActual);

        Assert.assertEquals(addressExpected, addressActual);

        Mockito.verify(addressRepository, Mockito.times(5)).findByZipCode(Mockito.any());

        Mockito.verify(addressRepository, Mockito.times(1)).findByZipCode(zipCode);
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

        Mockito.when(addressRepository.findByZipCode(zipCodeExpected)).thenReturn(Optional.ofNullable(addressMock));

        Address addressActual = validZipCodeHandler.check(zipCode);

        Assert.assertNotNull(addressActual);

        Assert.assertEquals(addressExpected, addressActual);

        Mockito.verify(addressRepository, Mockito.times(6)).findByZipCode(Mockito.any());

        Mockito.verify(addressRepository, Mockito.times(1)).findByZipCode(zipCode);
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

        Mockito.when(addressRepository.findByZipCode(zipCodeExpected)).thenReturn(Optional.ofNullable(addressMock));

        Address addressActual = validZipCodeHandler.check(zipCode);

        Assert.assertNotNull(addressActual);

        Assert.assertEquals(addressExpected, addressActual);

        Mockito.verify(addressRepository, Mockito.times(7)).findByZipCode(Mockito.any());

        Mockito.verify(addressRepository, Mockito.times(1)).findByZipCode(zipCode);
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

        Mockito.when(addressRepository.findByZipCode(zipCodeExpected)).thenReturn(Optional.ofNullable(addressMock));

        Address addressActual = validZipCodeHandler.check(zipCode);

        Assert.assertNotNull(addressActual);

        Assert.assertEquals(addressExpected, addressActual);

        Mockito.verify(addressRepository, Mockito.times(8)).findByZipCode(Mockito.any());

        Mockito.verify(addressRepository, Mockito.times(1)).findByZipCode(zipCode);
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

        Mockito.when(addressRepository.findByZipCode(zipCodeExpected)).thenReturn(Optional.ofNullable(addressMock));

        Address addressActual = validZipCodeHandler.check(zipCode);

        Assert.assertNotNull(addressActual);

        Assert.assertEquals(addressExpected, addressActual);

        Mockito.verify(addressRepository, Mockito.times(9)).findByZipCode(Mockito.any());

        Mockito.verify(addressRepository, Mockito.times(1)).findByZipCode(zipCode);
    }

    @Test
    public void testValidZipCodeNotFound() {
        String zipCode = "12345678";

        Mockito.when(addressRepository.findByZipCode(zipCode)).thenReturn(Optional.empty());

        Address addressActual = validZipCodeHandler.check(zipCode);

        Assert.assertNull(addressActual);

        Mockito.verify(addressRepository, Mockito.times(9)).findByZipCode(Mockito.any());

        Mockito.verify(addressRepository, Mockito.times(1)).findByZipCode("12345678");
        Mockito.verify(addressRepository, Mockito.times(1)).findByZipCode("12345670");
        Mockito.verify(addressRepository, Mockito.times(1)).findByZipCode("12345600");
        Mockito.verify(addressRepository, Mockito.times(1)).findByZipCode("12345000");
        Mockito.verify(addressRepository, Mockito.times(1)).findByZipCode("12340000");
        Mockito.verify(addressRepository, Mockito.times(1)).findByZipCode("12300000");
        Mockito.verify(addressRepository, Mockito.times(1)).findByZipCode("12000000");
        Mockito.verify(addressRepository, Mockito.times(1)).findByZipCode("10000000");
        Mockito.verify(addressRepository, Mockito.times(1)).findByZipCode("00000000");

    }
}
