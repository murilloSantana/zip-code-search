package com.zipcode.zipcodesearch.usecase.address.finder;

import com.zipcode.zipcodesearch.adapter.AddressRepository;
import com.zipcode.zipcodesearch.model.Address;
import com.zipcode.zipcodesearch.usecase.address.chain.AddressSearchChain;
import com.zipcode.zipcodesearch.usecase.address.chain.InvalidZipCodeException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AddressFinderImplTest {
    @InjectMocks
    private AddressFinderImpl addressFinderImpl;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressSearchChain addressSearchChain;

    @Test(expected = InvalidZipCodeException.class)
    public void testSearchInvalidZipCode() {
        addressFinderImpl.findAddressByZipCode("2223006");
    }

    @Test
    public void testSearchValidZipCodeFoundWithOneSearchs() {
        String zipCode = "22230061";
        String zipCodeExpected = "22230060";

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

        Address addressActual = addressFinderImpl.findAddressByZipCode(zipCode);

        Assert.assertEquals(addressExpected, addressActual);

        Mockito.verify(addressRepository, Mockito.times(2)).findByZipCode(Mockito.any());
        Mockito.verify(addressRepository, Mockito.times(1)).findByZipCode(zipCode);
    }

    @Test
    public void testSearchValidZipCodeFoundWithTwoSearchs() {
        String zipCode = "22230060";
        String zipCodeExpected = "22230060";

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

        Address addressActual = addressFinderImpl.findAddressByZipCode(zipCode);

        Assert.assertEquals(addressExpected, addressActual);

        Mockito.verify(addressRepository, Mockito.times(1)).findByZipCode(Mockito.any());
        Mockito.verify(addressRepository, Mockito.times(1)).findByZipCode(zipCode);
    }
}