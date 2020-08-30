package com.zipcode.zipcodesearch.address;

import com.zipcode.zipcodesearch.address.controller.AddressController;
import com.zipcode.zipcodesearch.address.controller.dto.AddressDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

public class AddressResponseUtil {

    private static final Logger log = LoggerFactory.getLogger(AddressController.class);

    public static ResponseEntity buildAddressListSuccessResponse(List<AddressDTO> addressDTOList) {
        log.info("Address List returned with success: ADDRESS_LIST {}", addressDTOList);

        return ResponseEntity.ok(addressDTOList);
    }

    public static ResponseEntity buildAddressCreatedResponse(AddressDTO addressDTO, String serverPort) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .port(serverPort)
                .path("/zipcode/{zipCode}")
                .buildAndExpand(addressDTO.getZipCode()).toUri();

        log.info("Address Created with success: ADDRESS {}", addressDTO);

        return ResponseEntity.created(location).build();
    }

    public static ResponseEntity buildAddressUpdatedResponse(AddressDTO addressDTO) {
        log.info("Address Updated with success: ADDRESS {}", addressDTO);

        return ResponseEntity.ok(addressDTO);
    }

    public static ResponseEntity buildAddressFoundResponse(AddressDTO addressDTO) {
        log.info("Address Found: ADDRESS {}", addressDTO);
        return ResponseEntity.ok(addressDTO);
    }

    public static ResponseEntity buildAddressNotFoundResponse(String zipCode) {
        log.info("Address Not Found: ZIP_CODE {}", zipCode);
        return ResponseEntity.notFound().build();
    }

    public static ResponseEntity buildAddressDeletedResponse(Long addressId) {
        log.info("Address Deleted: ADDRESS_ID {}", addressId);
        return ResponseEntity.ok().build();
    }
}
