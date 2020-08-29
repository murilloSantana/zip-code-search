package com.zipcode.zipcodesearch.address.service;

import com.zipcode.zipcodesearch.address.controller.dto.AddressDTO;
import com.zipcode.zipcodesearch.address.dataprovider.model.AddressData;
import com.zipcode.zipcodesearch.entity.Address;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<AddressDTO> addressToAddressDTO(List<Address> addressList) {
        return orikaMapperFacade.mapAsList(addressList, AddressDTO.class);
    }

    public Optional<AddressDTO> addressToAddressDTO(Optional<Address> address) {
        return address.map((addressUnwrapped) -> orikaMapperFacade.map(addressUnwrapped, AddressDTO.class));
    }

    public Optional<AddressDTO> addressToAddressDTO(Address address) {
        return Optional.ofNullable(orikaMapperFacade.map(address, AddressDTO.class));
    }

    public AddressData addressToAddressEntity(Address address) {
        return orikaMapperFacade.map(address, AddressData.class);
    }

    public Optional<Address> addressEntityToAddress(AddressData addressData) {
        return Optional.ofNullable(orikaMapperFacade.map(addressData, Address.class));
    }

    public List<Address> addressEntityToAddress(List<AddressData> addressDataList) {
        return orikaMapperFacade.mapAsList(addressDataList, Address.class);
    }

}
