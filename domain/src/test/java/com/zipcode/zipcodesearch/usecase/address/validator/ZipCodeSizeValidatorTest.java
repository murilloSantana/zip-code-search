package com.zipcode.zipcodesearch.usecase.address.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ZipCodeSizeValidatorTest {

    @InjectMocks
    private ZipCodeSizeValidator zipCodeSizeValidator;

    @Test
    public void testValidZipCode() {
        String zipCode = "23456789";
        Assertions.assertTrue(zipCodeSizeValidator.isValid(zipCode));
    }

    @Test
    public void testSmallInvalidZipCode() {
        String zipCode = "2345678";
        Assertions.assertFalse(zipCodeSizeValidator.isValid(zipCode));
    }

    @Test
    public void testLargeInvalidZipCode() {
        String zipCode = "234567822";
        Assertions.assertFalse(zipCodeSizeValidator.isValid(zipCode));
    }
}
