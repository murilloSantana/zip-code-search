package com.zipcode.zipcodesearch.address.service;

import com.zipcode.zipcodesearch.address.controller.dto.AddressDTO;
import com.zipcode.zipcodesearch.address.dataprovider.model.AddressData;
import com.zipcode.zipcodesearch.entity.Address;
import com.zipcode.zipcodesearch.entity.AddressNotFoundException;
import com.zipcode.zipcodesearch.entity.InvalidZipCodeException;
import com.zipcode.zipcodesearch.usecase.address.dataprovider.AddressUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import java.util.Arrays;
import java.util.List;
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
        return Optional.ofNullable(new AddressDTO("Rio de Janeiro",
                "Rio de Janeiro", "Flamengo", "Rua Marques de Abrantes", "22230060"));
    }

    public Optional<Address> mockAddress() {
        return Optional.ofNullable(new Address("Rio de Janeiro",
                "Rio de Janeiro", "Flamengo", "Rua Marques de Abrantes", "22230060"));
    }

    @Test
    public void testListAddresses() {
        List<AddressDTO> addressDTOListExpected = Arrays.asList(this.mockAddressDTO().get(), this.mockAddressDTO().get());
        List<Address> addressList = Arrays.asList(this.mockAddress().get(), this.mockAddress().get());

        when(this.addressUseCase.listAll()).thenReturn(addressList);
        when(this.addressConverter.addressToAddressDTO(addressList)).thenReturn(addressDTOListExpected);

        List<AddressDTO> addressDTOListActual = this.addressService.listAll();

        verify(this.addressUseCase, times(1)).listAll();
        verify(this.addressConverter, times(1)).addressToAddressDTO(addressList);

        assertEquals(addressDTOListExpected, addressDTOListActual);
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
        verify(this.addressConverter, times(0)).addressDataToAddress(any(AddressData.class));

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

    @Test
    public void testUpdate() {
        Long addressId = 2345678L;

        Optional<AddressDTO> addressDTOExpected = this.mockAddressDTO();
        Optional<Address> address = this.mockAddress();

        when(this.addressConverter.addressDTOToAddress(addressDTOExpected.get())).thenReturn(address.get());
        when(this.addressUseCase.update(addressId, address.get())).thenReturn(address);
        when(this.addressConverter.addressToAddressDTO(address)).thenReturn(addressDTOExpected);

        Optional<AddressDTO> addressDTOActual = this.addressService.update(addressId, addressDTOExpected.get());

        verify(this.addressConverter, times(1)).addressDTOToAddress(addressDTOExpected.get());
        verify(this.addressUseCase, times(1)).update(addressId, address.get());
        verify(this.addressConverter, times(1)).addressToAddressDTO(address);

        assertEquals(addressDTOExpected, addressDTOActual);
    }

    @Test
    public void testUpdateWithInvalidZipCode() {
        Long addressId = 2345678L;

        Optional<AddressDTO> addressDTO = this.mockAddressDTO();
        Optional<Address> address = this.mockAddress();
        addressDTO.get().setZipCode("2223006c");

        when(this.addressConverter.addressDTOToAddress(any())).thenReturn(address.get());
        when(this.addressUseCase.update(anyLong(), any())).thenThrow(InvalidZipCodeException.class);

        assertThrows(InvalidZipCodeException.class, () -> {
            this.addressService.update(addressId, addressDTO.get());
        });

        verify(this.addressConverter, times(1)).addressDTOToAddress(any());
        verify(this.addressUseCase, times(1)).update(anyLong(), any());
        verify(this.addressConverter, times(0)).addressToAddressDTO(any(Optional.class));
    }

    @Test
    public void testUpdateAddresstFound() {
        Long addressId = 2345678L;

        Optional<AddressDTO> addressDTO = this.mockAddressDTO();
        Optional<Address> address = this.mockAddress();
        addressDTO.get().setZipCode("2223006c");

        when(this.addressConverter.addressDTOToAddress(any())).thenReturn(address.get());
        when(this.addressUseCase.update(anyLong(), any())).thenThrow(AddressNotFoundException.class);

        assertThrows(AddressNotFoundException.class, () -> {
            this.addressService.update(addressId, addressDTO.get());
        });

        verify(this.addressConverter, times(1)).addressDTOToAddress(any());
        verify(this.addressUseCase, times(1)).update(anyLong(), any());
        verify(this.addressConverter, times(0)).addressToAddressDTO(any(Optional.class));
    }
}
