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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AddressUseCaseImplTest {
    @InjectMocks
    private AddressUseCaseImpl addressUseCaseImpl;

    @Mock
    private AddressDataProvider addressDataProvider;

    @Mock
    private AddressSearchChain addressSearchChain;

    @Test
    public void testSearchInvalidZipCode() {
        assertThrows(InvalidZipCodeException.class, () -> {
            addressUseCaseImpl.findByZipCode("2223006");
        });
    }

    public Optional<Address> mockAddress() {
        return Optional.ofNullable(Address
                .builder()
                .state("Rio de Janeiro")
                .city("Rio de Janeiro")
                .district("Flamengo")
                .street("Rua Marques de Abrantes")
                .zipCode("22230060")
                .build());
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

        Optional<Address> addressActual = addressUseCaseImpl.findByZipCode(zipCode);

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

        Optional<Address> addressActual = addressUseCaseImpl.findByZipCode(zipCode);

        assertEquals(addressExpected, addressActual.get());

        verify(addressDataProvider, times(1)).findByZipCode(any());
        verify(addressDataProvider, times(1)).findByZipCode(zipCode);
    }

    @Test
    public void testSaveWithInvalidZipCode() {
        Optional<Address> address = this.mockAddress();
        address.get().setZipCode("2223006c");

        assertThrows(InvalidZipCodeException.class, () -> {
            this.addressUseCaseImpl.save(address.get());
        });

        verify(this.addressDataProvider, times(0)).saveAddress(any());

    }

    @Test
    public void testListAddresses() {
        List<Address> addressListExpected = Arrays.asList(this.mockAddress().get(), this.mockAddress().get());

        when(this.addressDataProvider.listAll()).thenReturn(addressListExpected);

        List<Address> addressListActual = this.addressUseCaseImpl.listAll();

        verify(this.addressDataProvider, times(1)).listAll();

        assertEquals(addressListExpected, addressListActual);
    }

}