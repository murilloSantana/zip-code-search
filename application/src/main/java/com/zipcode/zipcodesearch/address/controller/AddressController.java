package com.zipcode.zipcodesearch.address.controller;

import com.zipcode.zipcodesearch.address.AddressResponseUtil;
import com.zipcode.zipcodesearch.address.controller.dto.AddressDTO;
import com.zipcode.zipcodesearch.address.service.AddressService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressService addressService;

    @Value("${server.port}")
    private String serverPort;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @ApiOperation(
            value = "List all addresses"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Listed addresses"),
                    @ApiResponse(code = 500, message = "Some problem occurred on the server")
            }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AddressDTO>> listAll() {
        return AddressResponseUtil.buildAddressListSuccessResponse(this.addressService.listAll());
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
    public ResponseEntity<Void> findByZipCode(@PathVariable("zipcode") String zipCode) {

        Optional<AddressDTO> addressDTO = this.addressService.findByZipCode(zipCode);

        if(addressDTO.isPresent()) return AddressResponseUtil.buildAddressFoundResponse(addressDTO.get());

        return AddressResponseUtil.buildAddressNotFoundResponse(zipCode);
    }

    @ApiOperation(
            value = "Create new address",
            notes = "In case of success, a URL is added to the Location header that allows the search for the newly created address"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Address created"),
                    @ApiResponse(code = 400, message = "Invalid Zip Code or missing required fields"),
                    @ApiResponse(code = 500, message = "Some problem occurred on the server")
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody @Valid AddressDTO addressDTO) {
        AddressDTO newAddress = this.addressService.save(addressDTO).get();

        return AddressResponseUtil.buildAddressCreatedResponse(newAddress, serverPort);
    }

    @ApiOperation(
            value = "Update address",
            notes = "The updated address is returned in the body"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Address updated"),
                    @ApiResponse(code = 400, message = "Invalid Zip Code or missing required fields or address not found"),
                    @ApiResponse(code = 500, message = "Some problem occurred on the server")
            }
    )
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDTO> update(@PathVariable("id") Long addressId, @RequestBody @Valid AddressDTO addressDTO) {
        AddressDTO newAddress = this.addressService.update(addressId, addressDTO).get();

        return AddressResponseUtil.buildAddressUpdatedResponse(newAddress);
    }

    @ApiOperation(
            value = "Delete address"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Address deleted"),
                    @ApiResponse(code = 400, message = "Address not found"),
                    @ApiResponse(code = 500, message = "Some problem occurred on the server")
            }
    )
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long addressId) {
        this.addressService.delete(addressId);
        return AddressResponseUtil.buildAddressDeletedResponse(addressId);
    }
}
