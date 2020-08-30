package com.zipcode.zipcodesearch.address.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipcode.zipcodesearch.address.controller.dto.AddressDTO;
import com.zipcode.zipcodesearch.address.service.AddressService;
import com.zipcode.zipcodesearch.entity.AddressNotFoundException;
import com.zipcode.zipcodesearch.entity.InvalidZipCodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @MockBean
    private AddressService addressService;

    @BeforeEach
    public void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    public Optional<AddressDTO> mockAddressDTO() {
        return Optional.ofNullable(new AddressDTO("Rio de Janeiro",
                "Rio de Janeiro", "Flamengo", "Rua Marques de Abrantes", "22230060"));
    }

    public List<AddressDTO> mockAddressDTOList() {
        AddressDTO firstAddress = new AddressDTO("Rio de Janeiro",
                "Rio de Janeiro", "Flamengo", "Rua Marques de Abrantes", "22230060");

        AddressDTO secondAddress = new AddressDTO("Rio de Janeiro",
                "Duque de Caxias", "PQ. Lafaiete", "Rua David de Oliveira", "22212345");
        return Arrays.asList(firstAddress, secondAddress);
    }

    @Test
    public void testListAllAddresses() throws Exception {
        List<AddressDTO> addressDTOList = this.mockAddressDTOList();

        when(addressService.listAll()).thenReturn(addressDTOList);

        this.mockMvc.perform(get("/address"))
                .andExpect(status().isOk())
                .andExpect(content().string(this.objectMapper.writeValueAsString(addressDTOList)));;
    }

    @Test
    public void testListAllAddressesThrowError() throws Exception {
        when(addressService.listAll()).thenThrow(RuntimeException.class);;

        this.mockMvc.perform(get("/address"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testAddressNotFound() throws Exception {
        String zipCode = "22231161";

        when(addressService.findByZipCode(zipCode)).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/address/zipcode/" + zipCode))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAddressFound() throws Exception {
        String zipCode = "22231161";

        when(addressService.findByZipCode(zipCode)).thenReturn(this.mockAddressDTO());

        this.mockMvc.perform(get("/address/zipcode/" + zipCode))
                .andExpect(status().isOk())
                .andExpect(content().string(this.objectMapper.writeValueAsString(this.mockAddressDTO().get())));
    }

    @Test
    public void testAddressNotFoundThrowError() throws Exception {
        String zipCode = "22231161";

        when(addressService.findByZipCode(zipCode)).thenThrow(RuntimeException.class);

        this.mockMvc.perform(get("/address/zipcode/" + zipCode))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testAddressThrowErrorWhenZipCodeIsInvalid() throws Exception {
        String zipCode = "22231161";

        when(addressService.findByZipCode(zipCode)).thenThrow(InvalidZipCodeException.class);

        this.mockMvc.perform(get("/address/zipcode/" + zipCode))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testSaveAddress() throws Exception {
        Optional<AddressDTO> addressDTO = this.mockAddressDTO();
        String parsedAddressDTO = this.objectMapper.writeValueAsString(addressDTO.get());
        when(addressService.save(addressDTO.get())).thenReturn(addressDTO);

        this.mockMvc.perform(post("/address").content(parsedAddressDTO).header("content-type", "application/json"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost:8080/address/zipcode/22230060"));
    }

    @Test
    public void testSaveAddressThrowError() throws Exception {
        Optional<AddressDTO> addressDTO = this.mockAddressDTO();
        String parsedAddressDTO = this.objectMapper.writeValueAsString(addressDTO.get());
        when(addressService.save(addressDTO.get())).thenThrow(RuntimeException.class);

        this.mockMvc.perform(post("/address").content(parsedAddressDTO).header("content-type", "application/json"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testSaveAddressThrowInvalidZipCodeException() throws Exception {
        Optional<AddressDTO> addressDTO = this.mockAddressDTO();
        String parsedAddressDTO = this.objectMapper.writeValueAsString(addressDTO.get());
        when(addressService.save(addressDTO.get())).thenThrow(InvalidZipCodeException.class);

        this.mockMvc.perform(post("/address/").content(parsedAddressDTO).header("content-type", "application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateAddress() throws Exception {
        Long addressId = 1234L;

        Optional<AddressDTO> addressDTO = this.mockAddressDTO();
        String parsedAddressDTO = this.objectMapper.writeValueAsString(addressDTO.get());
        when(addressService.update(addressId, addressDTO.get())).thenReturn(addressDTO);

        this.mockMvc.perform(put("/address/" + addressId).content(parsedAddressDTO).header("content-type", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string(parsedAddressDTO));
    }

    @Test
    public void testUpdateAddressThrowError() throws Exception {
        Long addressId = 1234L;

        Optional<AddressDTO> addressDTO = this.mockAddressDTO();
        String parsedAddressDTO = this.objectMapper.writeValueAsString(addressDTO.get());
        when(addressService.update(addressId, addressDTO.get())).thenThrow(RuntimeException.class);

        this.mockMvc.perform(put("/address/" + addressId).content(parsedAddressDTO).header("content-type", "application/json"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testUpdateAddressThrowInvalidZipCodeException() throws Exception {
        Long addressId = 1234L;

        Optional<AddressDTO> addressDTO = this.mockAddressDTO();
        String parsedAddressDTO = this.objectMapper.writeValueAsString(addressDTO.get());
        when(addressService.update(addressId, addressDTO.get())).thenThrow(InvalidZipCodeException.class);

        this.mockMvc.perform(put("/address/" + addressId).content(parsedAddressDTO).header("content-type", "application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateAddressThrowAddressNotFoundException() throws Exception {
        Long addressId = 1234L;

        Optional<AddressDTO> addressDTO = this.mockAddressDTO();
        String parsedAddressDTO = this.objectMapper.writeValueAsString(addressDTO.get());
        when(addressService.update(addressId, addressDTO.get())).thenThrow(AddressNotFoundException.class);

        this.mockMvc.perform(put("/address/" + addressId).content(parsedAddressDTO).header("content-type", "application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteAddress() throws Exception {
        Long addressId = 1234L;

        this.mockMvc.perform(delete("/address/" + addressId))
                .andExpect(status().isOk());

        verify(this.addressService, times(1)).delete(addressId);
    }

    @Test
    public void testDeleteAddressThrowError() throws Exception {
        Long addressId = 1234L;

        doThrow(RuntimeException.class).when(this.addressService).delete(addressId);

        this.mockMvc.perform(delete("/address/" + addressId))
                .andExpect(status().isInternalServerError());

        verify(this.addressService, times(1)).delete(addressId);

    }

    @Test
    public void testDeleteAddressThrowAddressNotFoundException() throws Exception {
        Long addressId = 1234L;

        doThrow(AddressNotFoundException.class).when(this.addressService).delete(addressId);

        this.mockMvc.perform(delete("/address/" + addressId))
                .andExpect(status().isBadRequest());

        verify(this.addressService, times(1)).delete(addressId);
    }
}
