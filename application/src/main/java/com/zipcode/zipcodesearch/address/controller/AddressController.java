package com.zipcode.zipcodesearch.address.controller;

import com.zipcode.zipcodesearch.address.controller.dto.AddressDTO;
import com.zipcode.zipcodesearch.address.service.AddressService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressService addressService;

    private static final Logger log = LoggerFactory.getLogger(AddressController.class);

    @Value("${server.port}")
    private String serverPort;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    private ResponseEntity buildAddressListSuccessResponse(List<AddressDTO> addressDTOList) {
        log.info("Address List returned with success: ADDRESS_LIST {}", addressDTOList);

        return ResponseEntity.ok(addressDTOList);
    }

    private ResponseEntity buildAddressCreatedResponse(AddressDTO addressDTO) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .port(serverPort)
                .path("/zipcode/{zipCode}")
                .buildAndExpand(addressDTO.getZipCode()).toUri();

        log.info("Address Created with success: ADDRESS {}", addressDTO);

        return ResponseEntity.created(location).build();
    }

    private ResponseEntity buildAddressUpdatedResponse(AddressDTO addressDTO) {
        log.info("Address Updated with success: ADDRESS {}", addressDTO);

        return ResponseEntity.ok(addressDTO);
    }

    private ResponseEntity buildAddressFoundResponse(AddressDTO addressDTO) {
        log.info("Address Found: ADDRESS {}", addressDTO);
        return ResponseEntity.ok(addressDTO);
    }

    private ResponseEntity buildAddressNotFoundResponse(String zipCode) {
        log.info("Address Not Found: ZIP_CODE {}", zipCode);
        return ResponseEntity.notFound().build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AddressDTO>> listAll() {
        return this.buildAddressListSuccessResponse(this.addressService.listAll());
    }

    @ApiOperation(
            value = "Retrieve address by Zip Code",
            notes = "This API doesn't work with Zip Code with dashes, for example \"22230-060\", the Zip Code must be informed " +
                    "only with numbers such as \"22230060\""
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Address found"),
                    @ApiResponse(code = 404, message = "Address not found"),
                    @ApiResponse(code = 400, message = "Invalid Zip Code"),
                    @ApiResponse(code = 500, message = "Some problem occurred on the server")
            }
    )
    @GetMapping(path = "/zipcode/{zipcode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDTO> findByZipCode(@PathVariable("zipcode") String zipCode) {

        Optional<AddressDTO> addressDTO = this.addressService.findByZipCode(zipCode);

        if(addressDTO.isPresent()) return this.buildAddressFoundResponse(addressDTO.get());

        return this.buildAddressNotFoundResponse(zipCode);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<URI> save(@RequestBody @Valid AddressDTO addressDTO) {
        AddressDTO newAddress = this.addressService.save(addressDTO).get();

        return this.buildAddressCreatedResponse(newAddress);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@PathVariable("id") Long addressId, @RequestBody @Valid AddressDTO addressDTO) {
        AddressDTO newAddress = this.addressService.update(addressId, addressDTO).get();

        return this.buildAddressUpdatedResponse(newAddress);
    }
}
