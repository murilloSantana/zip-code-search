package com.zipcode.zipcodesearch.controller.address;

import com.zipcode.zipcodesearch.controller.address.dto.AddressDTO;
import com.zipcode.zipcodesearch.service.AddressService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/address")
@Slf4j
public class AddressController {

    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    private ResponseEntity buildAddressFoundResponse(AddressDTO addressDTO) {
        log.info("Address Found: ADDRESS {}", addressDTO);
        return ResponseEntity.ok(addressDTO);
    }

    private ResponseEntity buildAddressNotFoundResponse(String zipCode) {
        log.info("Address Not Found: ZIP_CODE {}", zipCode);
        return ResponseEntity.notFound().build();
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
    @GetMapping(path = "/{zipCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDTO> findByZipCode(@PathVariable("zipCode") String zipCode) {

        Optional<AddressDTO> addressDTO = this.addressService.findAddressByZipCode(zipCode);

        if(addressDTO.isPresent()) return this.buildAddressFoundResponse(addressDTO.get());

        return this.buildAddressNotFoundResponse(zipCode);
    }

    @PostMapping
    public ResponseEntity<AddressDTO> save(@RequestBody AddressDTO addressDTO) {
        return ResponseEntity.ok(this.addressService.saveAddress(addressDTO).get());
    }

}
