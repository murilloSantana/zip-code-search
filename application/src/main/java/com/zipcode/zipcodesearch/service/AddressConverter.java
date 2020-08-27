package com.zipcode.zipcodesearch.service;

import com.zipcode.zipcodesearch.controller.address.dto.AddressDTO;
import com.zipcode.zipcodesearch.dataprovider.model.AddressEntity;
import com.zipcode.zipcodesearch.model.Address;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressConverter {

    private MapperFacade orikaMapperFacade;

    public AddressConverter(MapperFacade orikaMapperFacade) {
        this.orikaMapperFacade = orikaMapperFacade;
    }

    public Address addressDTOToAddress(AddressDTO addressDTO) {
        return orikaMapperFacade.map(addressDTO, Address.class);
    }

    public Optional<AddressDTO> addressToAddressDTO(Optional<Address> address) {
        return address.map((addressUnwrapped) -> orikaMapperFacade.map(addressUnwrapped, AddressDTO.class));
    }

    public Optional<AddressDTO> addressToAddressDTO(Address address) {
        return Optional.ofNullable(orikaMapperFacade.map(address, AddressDTO.class));
    }

    public AddressEntity addressToAddressEntity(Address address) {
        return orikaMapperFacade.map(address, AddressEntity.class);
    }

    public Optional<Address> addressEntityToAddress(AddressEntity addressEntity) {
        return Optional.ofNullable(orikaMapperFacade.map(addressEntity, Address.class));
    }

}
