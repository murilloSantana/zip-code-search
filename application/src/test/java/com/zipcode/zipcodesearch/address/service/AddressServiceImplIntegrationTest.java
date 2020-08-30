package com.zipcode.zipcodesearch.address.service;

import com.zipcode.zipcodesearch.Application;
import com.zipcode.zipcodesearch.address.controller.dto.AddressDTO;
import com.zipcode.zipcodesearch.entity.Address;
import com.zipcode.zipcodesearch.usecase.address.dataprovider.AddressUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = Application.class)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
public class AddressServiceImplIntegrationTest {

    @Autowired
    private AddressServiceImpl addressService;

    @Mock
    private AddressUseCase addressUseCase;

    @Mock
    private AddressConverter addressConverter;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    public void setUp() {
        addressService.setAddressUseCase(addressUseCase);
        addressService.setAddressConverter(addressConverter);
    }

    public Optional<AddressDTO> mockAddressDTO() {
        return Optional.ofNullable(new AddressDTO("Rio de Janeiro",
                "Rio de Janeiro", "Flamengo", "Rua Marques de Abrantes", "22230060"));
    }

    public Optional<Address> mockAddress() {
        return Optional.ofNullable(new Address("Rio de Janeiro",
                "Rio de Janeiro", "Flamengo", "Rua Marques de Abrantes", "22230060"));
    }

    @Test
    public void testFoundAddressByZipCodeWithCache() {
        String zipCode = "22230060";

        Optional<AddressDTO> addressDTOExpected = this.mockAddressDTO();
        Optional<Address> address = this.mockAddress();

        when(this.addressUseCase.findByZipCode(zipCode)).thenReturn(address);
        when(this.addressConverter.addressToAddressDTO(address.get())).thenReturn(addressDTOExpected);

        Optional<AddressDTO> firstAddressDTOResponse = this.addressService.findByZipCode(zipCode);
        Optional<AddressDTO> secondAddressDTOResponse = this.addressService.findByZipCode(zipCode);

        verify(this.addressUseCase, times(1)).findByZipCode(zipCode);
        verify(this.addressConverter, times(1)).addressToAddressDTO(address.get());

        assertEquals(addressDTOExpected, firstAddressDTOResponse);
        assertEquals(addressDTOExpected, secondAddressDTOResponse);
    }

    @Test
    public void testNotFoundAddressByZipCodeWithCache() {
        String zipCode = "22230160";

        Optional<AddressDTO> addressDTOExpected = this.mockAddressDTO();
        Optional<Address> address = this.mockAddress();

        when(this.addressUseCase.findByZipCode(zipCode)).thenReturn(Optional.empty());
        when(this.addressConverter.addressToAddressDTO(address.get())).thenReturn(addressDTOExpected);

        Optional<AddressDTO> firstAddressDTOResponse = this.addressService.findByZipCode(zipCode);
        Optional<AddressDTO> secondAddressDTOResponse = this.addressService.findByZipCode(zipCode);

        verify(this.addressUseCase, times(2)).findByZipCode(zipCode);
        verify(this.addressConverter, times(0)).addressToAddressDTO(address.get());

        assertEquals(Optional.empty(), firstAddressDTOResponse);
        assertEquals(Optional.empty(), secondAddressDTOResponse);
    }

    @Test
    public void testDeleteAddress() {
        String zipCode = "22230060";
        Long addressId = 2345678L;

        Optional<AddressDTO> addressDTOExpected = this.mockAddressDTO();
        Optional<Address> address = this.mockAddress();

        when(this.addressUseCase.findByZipCode(zipCode)).thenReturn(address);
        when(this.addressConverter.addressToAddressDTO(address.get())).thenReturn(addressDTOExpected);

        Optional<AddressDTO> addressDTOActual = this.addressService.findByZipCode(zipCode);

        assertEquals(addressDTOExpected, addressDTOActual);
        assertNotNull(cacheManager.getCache("addressByZipCode").get(zipCode));

        when(this.addressUseCase.findById(addressId)).thenReturn(address);

        this.addressService.delete(addressId);

        verify(this.addressUseCase, times(1)).findById(addressId);
        verify(this.addressUseCase, times(1)).delete(addressId);

        assertNull(cacheManager.getCache("addressByZipCode").get(zipCode));
    }

}
