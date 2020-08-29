package com.zipcode.zipcodesearch.address.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipcode.zipcodesearch.address.controller.dto.AddressDTO;
import com.zipcode.zipcodesearch.address.service.AddressService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        return Optional.ofNullable(AddressDTO
                .builder()
                .state("Rio de Janeiro")
                .city("Rio de Janeiro")
                .district("Flamengo")
                .street("Rua Marques de Abrantes")
                .zipCode("22230060")
                .build());
    }

    public List<AddressDTO> mockAddressDTOList() {
        AddressDTO firstAddress = AddressDTO
                .builder()
                .state("Rio de Janeiro")
                .city("Rio de Janeiro")
                .district("Flamengo")
                .street("Rua Marques de Abrantes")
                .zipCode("22230060")
                .build();

        AddressDTO secondAddress = AddressDTO
                .builder()
                .state("Rio de Janeiro")
                .city("Duque de Caxias")
                .district("PQ. Lafaiete")
                .street("Rua David de Oliveira")
                .zipCode("22212345")
                .build();

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
}
