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

    public Optional<AddressData> mockAddressEntity() {
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

        Optional<AddressData> addressEntity = this.mockAddressEntity();
        Optional<Address> addressExpected = this.mockAddress();

        when(this.addressRepository.findByZipCode(zipCode)).thenReturn(addressEntity);
        when(this.addressConverter.addressEntityToAddress(addressEntity.get())).thenReturn(addressExpected);

        Optional<Address> addressActual = this.addressDataProviderAdapter.findByZipCode(zipCode);

        verify(this.addressRepository, times(1)).findByZipCode(zipCode);
        verify(this.addressConverter, times(1)).addressEntityToAddress(addressEntity.get());

        assertEquals(addressExpected, addressActual);
    }

    @Test
    public void testNotFoundAddressByZipCode() {
        String zipCode = "22230060";

        when(this.addressRepository.findByZipCode(zipCode)).thenReturn(Optional.empty());

        Optional<Address> addressActual = this.addressDataProviderAdapter.findByZipCode(zipCode);

        verify(this.addressRepository, times(1)).findByZipCode(zipCode);
        verify(this.addressConverter, times(0)).addressEntityToAddress(any(AddressData.class));

        assertEquals(Optional.empty(), addressActual);
    }

    @Test
    public void testSaveAddress() {
        Optional<AddressData> addressEntity = this.mockAddressEntity();
        Optional<Address> address = this.mockAddress();

        when(this.addressConverter.addressToAddressEntity(any())).thenReturn(addressEntity.get());
        when(this.addressRepository.save(any())).thenReturn(addressEntity.get());
        when(this.addressConverter.addressEntityToAddress(any(AddressData.class))).thenReturn(address);

        Optional<Address> addressActual = this.addressDataProviderAdapter.save(address.get());

        verify(this.addressConverter, times(1)).addressToAddressEntity(address.get());
        verify(this.addressRepository, times(1)).save(addressEntity.get());
        verify(this.addressConverter, times(1)).addressEntityToAddress(addressEntity.get());

        assertEquals(address, addressActual);
    }

}
