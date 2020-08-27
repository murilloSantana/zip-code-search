package com.zipcode.zipcodesearch.service;

import com.zipcode.zipcodesearch.dataprovider.model.AddressEntity;
import com.zipcode.zipcodesearch.dataprovider.repository.AddressDataProviderAdapter;
import com.zipcode.zipcodesearch.dataprovider.repository.AddressRepository;
import com.zipcode.zipcodesearch.model.Address;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceImplTest {

    @InjectMocks
    private AddressDataProviderAdapter addressDataProviderAdapter;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressConverter addressConverter;

    public Optional<AddressEntity> mockAddressEntity() {
        return Optional.ofNullable(AddressEntity
                .builder()
                .state("Rio de Janeiro")
                .city("Rio de Janeiro")
                .district("Flamengo")
                .street("Rua Marques de Abrantes")
                .zipCode("22230060")
                .build());
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
    public void testFoundAddressByZipCode() {
        String zipCode = "22230060";

        Optional<AddressEntity> addressEntity = this.mockAddressEntity();
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
        verify(this.addressConverter, times(0)).addressEntityToAddress(any());

        assertEquals(Optional.empty(), addressActual);
    }

    @Test
    public void testSaveAddress() {
        Optional<AddressEntity> addressEntity = this.mockAddressEntity();
        Optional<Address> address = this.mockAddress();

        when(this.addressConverter.addressToAddressEntity(any())).thenReturn(addressEntity.get());
        when(this.addressRepository.save(any())).thenReturn(addressEntity.get());
        when(this.addressConverter.addressEntityToAddress(any())).thenReturn(address);

        Optional<Address> addressActual = this.addressDataProviderAdapter.saveAddress(address.get());

        verify(this.addressConverter, times(1)).addressToAddressEntity(address.get());
        verify(this.addressRepository, times(1)).save(addressEntity.get());
        verify(this.addressConverter, times(1)).addressEntityToAddress(addressEntity.get());

        assertEquals(address, addressActual);
    }
}
