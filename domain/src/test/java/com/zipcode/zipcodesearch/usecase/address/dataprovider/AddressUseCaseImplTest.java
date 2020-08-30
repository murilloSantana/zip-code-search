package com.zipcode.zipcodesearch.usecase.address.dataprovider;

import com.zipcode.zipcodesearch.entity.Address;
import com.zipcode.zipcodesearch.entity.AddressNotFoundException;
import com.zipcode.zipcodesearch.entity.InvalidZipCodeException;
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

    private Optional<Address> mockAddress() {
        return Optional.ofNullable(new Address("Rio de Janeiro",
                "Rio de Janeiro", "Flamengo", "Rua Marques de Abrantes", "22230060"));
    }

    private Address mockAddress(String zipCode) {
        return new Address("Rio de Janeiro",
                "Rio de Janeiro", "Flamengo", "Rua Marques de Abrantes", zipCode);
    }

    @Test
    public void testSearchValidZipCodeFoundWithOneSearchs() {
        String zipCode = "22230061";
        String zipCodeExpected = "22230060";

        Address addressExpected = this.mockAddress(zipCodeExpected);

        Address addressMock = this.mockAddress(zipCodeExpected);

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

        Address addressExpected = this.mockAddress(zipCodeExpected);

        Address addressMock = this.mockAddress(zipCodeExpected);

        when(addressDataProvider.findByZipCode(zipCodeExpected)).thenReturn(Optional.ofNullable(addressMock));

        Optional<Address> addressActual = addressUseCaseImpl.findByZipCode(zipCode);

        assertEquals(addressExpected, addressActual.get());

        verify(addressDataProvider, times(1)).findByZipCode(any());
        verify(addressDataProvider, times(1)).findByZipCode(zipCode);
    }

    @Test
    public void testSave() {
        Optional<Address> addressExpected = this.mockAddress();
        addressExpected.get().setZipCode("22230062");

        when(addressDataProvider.save(addressExpected.get())).thenReturn(addressExpected);

        Optional<Address> addressActual = this.addressUseCaseImpl.save(addressExpected.get());

        verify(this.addressDataProvider, times(1)).save(any());

        assertEquals(addressExpected, addressActual);
    }

    @Test
    public void testSaveWithInvalidZipCode() {
        Optional<Address> address = this.mockAddress();
        address.get().setZipCode("2223006c");

        assertThrows(InvalidZipCodeException.class, () -> {
            this.addressUseCaseImpl.save(address.get());
        });

        verify(this.addressDataProvider, times(0)).save(any());
    }

    @Test
    public void testUpdate() {
        Long addressId = 2345678L;
        Optional<Address> addressExpected = this.mockAddress();
        addressExpected.get().setZipCode("22230062");

        when(addressDataProvider.existsById(addressId)).thenReturn(true);
        when(addressDataProvider.save(addressExpected.get())).thenReturn(addressExpected);

        Optional<Address> addressActual = this.addressUseCaseImpl.update(addressId, addressExpected.get());

        verify(this.addressDataProvider, times(1)).existsById(anyLong());
        verify(this.addressDataProvider, times(1)).save(any());

        assertEquals(addressExpected, addressActual);
    }

    @Test
    public void testUpdateWithInvalidZipCode() {
        Long addressId = 2345678L;
        Optional<Address> address = this.mockAddress();
        address.get().setZipCode("2223006c");

        when(addressDataProvider.existsById(addressId)).thenReturn(true);

        assertThrows(InvalidZipCodeException.class, () -> {
            this.addressUseCaseImpl.update(addressId, address.get());
        });

        verify(this.addressDataProvider, times(1)).existsById(anyLong());
        verify(this.addressDataProvider, times(0)).save(any());
    }

    @Test
    public void testUpdateAddressNotFound() {
        Long addressId = 2345678L;
        Optional<Address> address = this.mockAddress();
        address.get().setZipCode("2223006c");

        when(this.addressDataProvider.existsById(addressId)).thenReturn(false);

        assertThrows(AddressNotFoundException.class, () -> {
            this.addressUseCaseImpl.update(addressId, address.get());
        });

        verify(this.addressDataProvider, times(1)).existsById(anyLong());
        verify(this.addressDataProvider, times(0)).save(any());
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