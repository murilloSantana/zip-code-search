package com.zipcode.zipcodesearch.address.dataprovider.repository;

import com.zipcode.zipcodesearch.address.dataprovider.model.AddressData;
import com.zipcode.zipcodesearch.entity.Address;
import com.zipcode.zipcodesearch.address.service.AddressConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class AddressDataProviderAdapterTest {
    @InjectMocks
    private AddressDataProviderAdapter addressDataProviderAdapter;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressConverter addressConverter;

    public Optional<AddressData> mockAddressData() {
        return Optional.ofNullable(new AddressData("Rio de Janeiro",
                "Rio de Janeiro", "Flamengo", "Rua Marques de Abrantes", "22230060"));
    }

    public Optional<Address> mockAddress() {
        return Optional.ofNullable(new Address("Rio de Janeiro",
                "Rio de Janeiro", "Flamengo", "Rua Marques de Abrantes", "22230060"));
    }

    @Test
    public void testFoundAddressByZipCode() {
        String zipCode = "22230060";

        Optional<AddressData> addressData = this.mockAddressData();
        Optional<Address> addressExpected = this.mockAddress();

        when(this.addressRepository.findByZipCode(zipCode)).thenReturn(addressData);
        when(this.addressConverter.addressDataToAddress(addressData.get())).thenReturn(addressExpected);

        Optional<Address> addressActual = this.addressDataProviderAdapter.findByZipCode(zipCode);

        verify(this.addressRepository, times(1)).findByZipCode(zipCode);
        verify(this.addressConverter, times(1)).addressDataToAddress(addressData.get());

        assertEquals(addressExpected, addressActual);
    }

    @Test
    public void testNotFoundAddressByZipCode() {
        String zipCode = "22230060";

        when(this.addressRepository.findByZipCode(zipCode)).thenReturn(Optional.empty());

        Optional<Address> addressActual = this.addressDataProviderAdapter.findByZipCode(zipCode);

        verify(this.addressRepository, times(1)).findByZipCode(zipCode);
        verify(this.addressConverter, times(0)).addressDataToAddress(any(AddressData.class));

        assertEquals(Optional.empty(), addressActual);
    }

    @Test
    public void testSaveAddress() {
        Optional<AddressData> addressData = this.mockAddressData();
        Optional<Address> address = this.mockAddress();

        when(this.addressConverter.addressToAddressData(any())).thenReturn(addressData.get());
        when(this.addressRepository.save(any())).thenReturn(addressData.get());
        when(this.addressConverter.addressDataToAddress(any(AddressData.class))).thenReturn(address);

        Optional<Address> addressActual = this.addressDataProviderAdapter.save(address.get());

        verify(this.addressConverter, times(1)).addressToAddressData(address.get());
        verify(this.addressRepository, times(1)).save(addressData.get());
        verify(this.addressConverter, times(1)).addressDataToAddress(addressData.get());

        assertEquals(address, addressActual);
    }

    @Test
    public void testFoundAddressById() {
        Long addressId = 2345678L;

        Optional<AddressData> addressData = this.mockAddressData();
        Optional<Address> addressExpected = this.mockAddress();

        when(this.addressRepository.findById(addressId)).thenReturn(addressData);
        when(this.addressConverter.addressDataToAddress(addressData.get())).thenReturn(addressExpected);

        Optional<Address> addressActual = this.addressDataProviderAdapter.findById(addressId);

        verify(this.addressRepository, times(1)).findById(addressId);
        verify(this.addressConverter, times(1)).addressDataToAddress(addressData.get());

        assertEquals(addressExpected, addressActual);
    }

    @Test
    public void testNotFoundAddressById() {
        Long addressId = 2345678L;

        when(this.addressRepository.findById(addressId)).thenReturn(Optional.empty());

        Optional<Address> addressActual = this.addressDataProviderAdapter.findById(addressId);

        verify(this.addressRepository, times(1)).findById(addressId);
        verify(this.addressConverter, times(0)).addressDataToAddress(any(AddressData.class));

        assertEquals(Optional.empty(), addressActual);
    }

    @Test
    public void testDeleteAddressById() {
        Long addressId = 2345678L;

        this.addressDataProviderAdapter.delete(addressId);

        verify(this.addressRepository, times(1)).deleteById(addressId);
    }
}
