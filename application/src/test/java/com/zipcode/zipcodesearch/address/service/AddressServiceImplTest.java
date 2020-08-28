package com.zipcode.zipcodesearch.address.service;

import com.zipcode.zipcodesearch.address.controller.dto.AddressDTO;
import com.zipcode.zipcodesearch.model.Address;
import com.zipcode.zipcodesearch.model.InvalidZipCodeException;
import com.zipcode.zipcodesearch.usecase.address.dataprovider.AddressUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceImplTest {

    @InjectMocks
    private AddressServiceImpl addressService;

    @Mock
    private AddressUseCase addressUseCase;

    @Mock
    private AddressConverter addressConverter;

    @Autowired
    private CacheManager cacheManager;

    public Optional<AddressDTO> mockAddressDTO() {
        return Optional.ofNullable(AddressDTO
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

        Optional<AddressDTO> addressDTOExpected = this.mockAddressDTO();
        Optional<Address> address = this.mockAddress();

        when(this.addressUseCase.findByZipCode(zipCode)).thenReturn(address);
        when(this.addressConverter.addressToAddressDTO(address.get())).thenReturn(addressDTOExpected);

        Optional<AddressDTO> addressDTOActual = this.addressService.findByZipCode(zipCode);

        verify(this.addressUseCase, times(1)).findByZipCode(zipCode);
        verify(this.addressConverter, times(1)).addressToAddressDTO(address.get());

        assertEquals(addressDTOExpected, addressDTOActual);
    }

    @Test
    public void testNotFoundAddressByZipCode() {
        String zipCode = "22230060";

        when(this.addressUseCase.findByZipCode(zipCode)).thenReturn(Optional.empty());

        Optional<AddressDTO> addressDTOActual = this.addressService.findByZipCode(zipCode);

        verify(this.addressUseCase, times(1)).findByZipCode(zipCode);
        verify(this.addressConverter, times(0)).addressEntityToAddress(any());

        assertEquals(Optional.empty(), addressDTOActual);
    }

    @Test
    public void testSave() {
        Optional<AddressDTO> addressDTOExpected = this.mockAddressDTO();
        Optional<Address> address = this.mockAddress();

        when(this.addressConverter.addressDTOToAddress(addressDTOExpected.get())).thenReturn(address.get());
        when(this.addressUseCase.save(address.get())).thenReturn(address);
        when(this.addressConverter.addressToAddressDTO(address)).thenReturn(addressDTOExpected);

        Optional<AddressDTO> addressDTOActual = this.addressService.save(addressDTOExpected.get());

        verify(this.addressConverter, times(1)).addressDTOToAddress(addressDTOExpected.get());
        verify(this.addressUseCase, times(1)).save(address.get());
        verify(this.addressConverter, times(1)).addressToAddressDTO(address);

        assertEquals(addressDTOExpected, addressDTOActual);
    }

    @Test
    public void testSaveWithInvalidZipCode() {
        Optional<AddressDTO> addressDTO = this.mockAddressDTO();
        Optional<Address> address = this.mockAddress();
        addressDTO.get().setZipCode("2223006c");

        when(this.addressConverter.addressDTOToAddress(any())).thenReturn(address.get());
        when(this.addressUseCase.save(any())).thenThrow(InvalidZipCodeException.class);

        assertThrows(InvalidZipCodeException.class, () -> {
            this.addressService.save(addressDTO.get());
        });

        verify(this.addressConverter, times(1)).addressDTOToAddress(any());
        verify(this.addressUseCase, times(1)).save(any());
        verify(this.addressConverter, times(0)).addressToAddressDTO(any(Optional.class));
    }
}
