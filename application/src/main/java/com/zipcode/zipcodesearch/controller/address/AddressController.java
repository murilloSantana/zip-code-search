package com.zipcode.zipcodesearch.controller.address;

import com.zipcode.zipcodesearch.controller.address.dto.AddressDTO;
import com.zipcode.zipcodesearch.usecase.address.finder.AddressFinder;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressFinder addressFinder;

    public AddressController(AddressFinder addressFinder) {
        this.addressFinder = addressFinder;
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
        return addressFinder.findAddressByZipCode(zipCode)
                .map((address) -> ResponseEntity.ok(AddressDTO.addressToAddressDTO(address)))
                .orElse(ResponseEntity.notFound().build());
    }
}
