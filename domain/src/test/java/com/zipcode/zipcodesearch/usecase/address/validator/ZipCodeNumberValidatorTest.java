package com.zipcode.zipcodesearch.usecase.address.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ZipCodeNumberValidatorTest {

    @InjectMocks
    private ZipCodeNumberValidator zipCodeNumberValidator;

    @Test
    public void testValidZipCode() {
        String zipCode = "23456789";
        assertTrue(zipCodeNumberValidator.isValid(zipCode));
    }

    @Test
    public void testInvalidZipCodeWithLetters() {
        String zipCode = "234567c";
        assertFalse(zipCodeNumberValidator.isValid(zipCode));
    }

    @Test
    public void testInvalidZipCodeWithSpace() {
        String zipCode = "2345678 ";
        assertFalse(zipCodeNumberValidator.isValid(zipCode));
    }

    @Test
    public void testInvalidZipCodeWithSpecialCharacter() {
        String zipCode = "2345678$";
        assertFalse(zipCodeNumberValidator.isValid(zipCode));
    }
}
