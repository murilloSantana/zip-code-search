package com.zipcode.zipcodesearch.service;


import com.zipcode.zipcodesearch.controller.address.dto.AddressDTO;
import com.zipcode.zipcodesearch.dataprovider.model.AddressEntity;
import com.zipcode.zipcodesearch.model.Address;
import ma.glasnost.orika.MapperFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    public AddressEntity mockAddressEntity() {
        return AddressEntity
                .builder()
                .state("Rio de Janeiro")
                .city("Rio de Janeiro")
                .district("Flamengo")
                .street("Rua Marques de Abrantes")
                .zipCode("22230060")
                .build();
    }

    public Address mockAddress() {
        return Address
                .builder()
                .state("Rio de Janeiro")
                .city("Rio de Janeiro")
                .district("Flamengo")
                .street("Rua Marques de Abrantes")
                .zipCode("22230060")
                .build();
    }

    public AddressDTO mockAddressDTO() {
        return AddressDTO
                .builder()
                .state("Rio de Janeiro")
                .city("Rio de Janeiro")
                .district("Flamengo")
                .street("Rua Marques de Abrantes")
                .zipCode("22230060")
                .build();
    }

    @Test
    public void testConvertAddressDTOToAddress() {
        AddressDTO addressDTO = this.mockAddressDTO();
        Address addressExpected = this.mockAddress();
        Address addressActual = this.addressConverter.addressDTOToAddress(addressDTO);

        assertEquals(addressExpected, addressActual);
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
        AddressEntity addressEntityExpected = this.mockAddressEntity();
        AddressEntity addressActual = this.addressConverter.addressToAddressEntity(address);

        assertEquals(addressEntityExpected, addressActual);
    }

    @Test
    public void testConvertAddressEntityToAddress() {
        AddressEntity addressEntity = this.mockAddressEntity();
        Address addressExpected = this.mockAddress();
        Optional<Address> addressActual = this.addressConverter.addressEntityToAddress(addressEntity);

        assertEquals(addressExpected, addressActual.get());
    }

}
