package com.zipcode.zipcodesearch.usecase.address.dataprovider;

import com.zipcode.zipcodesearch.model.Address;
import com.zipcode.zipcodesearch.model.InvalidZipCodeException;
import com.zipcode.zipcodesearch.usecase.address.chain.AddressSearchChain;
import com.zipcode.zipcodesearch.usecase.address.dataprovider.adapter.AddressDataProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AddressUseCaseImplTest {
    @InjectMocks
    private AddressUseCaseImpl addressFinderImpl;

    @Mock
    private AddressDataProvider addressDataProvider;

    @Mock
    private AddressSearchChain addressSearchChain;

    @Test
    public void testSearchInvalidZipCode() {
        assertThrows(InvalidZipCodeException.class, () -> {
            addressFinderImpl.findByZipCode("2223006");
        });
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

        when(addressDataProvider.findByZipCode(zipCodeExpected)).thenReturn(Optional.ofNullable(addressMock));

        Optional<Address> addressActual = addressFinderImpl.findByZipCode(zipCode);

        assertEquals(addressExpected, addressActual.get());

        verify(addressDataProvider, times(2)).findByZipCode(any());
        verify(addressDataProvider, times(1)).findByZipCode(zipCode);
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

        when(addressDataProvider.findByZipCode(zipCodeExpected)).thenReturn(Optional.ofNullable(addressMock));

        Optional<Address> addressActual = addressFinderImpl.findByZipCode(zipCode);

        assertEquals(addressExpected, addressActual.get());

        verify(addressDataProvider, times(1)).findByZipCode(any());
        verify(addressDataProvider, times(1)).findByZipCode(zipCode);
    }
}