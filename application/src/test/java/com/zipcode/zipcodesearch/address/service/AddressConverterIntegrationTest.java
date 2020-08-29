package com.zipcode.zipcodesearch.address.service;


import com.zipcode.zipcodesearch.address.controller.dto.AddressDTO;
import com.zipcode.zipcodesearch.address.dataprovider.model.AddressData;
import com.zipcode.zipcodesearch.entity.Address;
import ma.glasnost.orika.MapperFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AddressConverterIntegrationTest {

    private AddressConverter addressConverter;

    @Autowired
    private MapperFacade orikaMapperFacade;

    @BeforeEach
    public void setUp() {
        this.addressConverter = new AddressConverter(this.orikaMapperFacade);
    }

    public AddressData mockAddressEntity() {
        return new AddressData("Rio de Janeiro", "Rio de Janeiro",
                "Flamengo", "Rua Marques de Abrantes", "22230060");
    }

    public Address mockAddress() {
        return new Address("Rio de Janeiro", "Rio de Janeiro",
                "Flamengo", "Rua Marques de Abrantes", "22230060");
    }

    public AddressDTO mockAddressDTO() {
        return new AddressDTO("Rio de Janeiro", "Rio de Janeiro", "Flamengo", "Rua Marques de Abrantes", "22230060");
    }

    @Test
    public void testConvertAddressDTOToAddress() {
        AddressDTO addressDTO = this.mockAddressDTO();
        Address addressExpected = this.mockAddress();
        Address addressActual = this.addressConverter.addressDTOToAddress(addressDTO);

        assertEquals(addressExpected, addressActual);
    }

    @Test
    public void testConvertAddressListToAddressDTOList() {
        List<Address> addressList = Arrays.asList(this.mockAddress(), this.mockAddress());
        List<AddressDTO> addressDTOListExpected = Arrays.asList(this.mockAddressDTO(), this.mockAddressDTO());
        List<AddressDTO> addressDTOListActual = this.addressConverter.addressToAddressDTO(addressList);

        assertEquals(addressDTOListExpected, addressDTOListActual);
    }

    @Test
    public void testConvertAddressToAddressDTO() {
        Address address = this.mockAddress();
        AddressDTO addressDTOExpected = this.mockAddressDTO();
        Optional<AddressDTO> addressDTOActual = this.addressConverter.addressToAddressDTO(address);

        assertEquals(addressDTOExpected, addressDTOActual.get());
    }

    @Test
    public void testConvertOptionalAddressToAddressDTO() {
        Optional<Address> address = Optional.ofNullable(this.mockAddress());
        AddressDTO addressDTOExpected = this.mockAddressDTO();
        Optional<AddressDTO> addressDTOActual = this.addressConverter.addressToAddressDTO(address);

        assertEquals(addressDTOExpected, addressDTOActual.get());
    }

    @Test
    public void testConvertAddressToAddressEntity() {
        Address address = this.mockAddress();
        AddressData addressDataExpected = this.mockAddressEntity();
        AddressData addressActual = this.addressConverter.addressToAddressEntity(address);

        assertEquals(addressDataExpected, addressActual);
    }

    @Test
    public void testConvertAddressEntityToAddress() {
        AddressData addressData = this.mockAddressEntity();
        Address addressExpected = this.mockAddress();
        Optional<Address> addressActual = this.addressConverter.addressEntityToAddress(addressData);

        assertEquals(addressExpected, addressActual.get());
    }

    @Test
    public void testConvertAddressEntityListToAddressList() {
        List<AddressData> addressDataList = Arrays.asList(this.mockAddressEntity(), this.mockAddressEntity());
        List<Address> addressListExpected = Arrays.asList(this.mockAddress(), this.mockAddress());
        List<Address> addressListActual = this.addressConverter.addressEntityToAddress(addressDataList);

        assertEquals(addressListExpected, addressListActual);
    }


}
