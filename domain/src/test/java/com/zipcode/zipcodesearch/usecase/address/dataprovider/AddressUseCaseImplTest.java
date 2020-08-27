package com.zipcode.zipcodesearch.usecase.address.dataprovider;

import com.zipcode.zipcodesearch.usecase.address.dataprovider.adapter.AddressDataProvider;
import com.zipcode.zipcodesearch.model.Address;
import com.zipcode.zipcodesearch.usecase.address.chain.AddressSearchChain;
import com.zipcode.zipcodesearch.model.InvalidZipCodeException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AddressUseCaseImplTest {
    @InjectMocks
    private AddressUseCaseImpl addressFinderImpl;

    @Mock
    private AddressDataProvider addressDataProvider;

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

        Mockito.when(addressDataProvider.findByZipCode(zipCodeExpected)).thenReturn(Optional.ofNullable(addressMock));

        Optional<Address> addressActual = addressFinderImpl.findAddressByZipCode(zipCode);

        Assert.assertEquals(addressExpected, addressActual.get());

        Mockito.verify(addressDataProvider, Mockito.times(2)).findByZipCode(Mockito.any());
        Mockito.verify(addressDataProvider, Mockito.times(1)).findByZipCode(zipCode);
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

        Mockito.when(addressDataProvider.findByZipCode(zipCodeExpected)).thenReturn(Optional.ofNullable(addressMock));

        Optional<Address> addressActual = addressFinderImpl.findAddressByZipCode(zipCode);

        Assert.assertEquals(addressExpected, addressActual.get());

        Mockito.verify(addressDataProvider, Mockito.times(1)).findByZipCode(Mockito.any());
        Mockito.verify(addressDataProvider, Mockito.times(1)).findByZipCode(zipCode);
    }
}