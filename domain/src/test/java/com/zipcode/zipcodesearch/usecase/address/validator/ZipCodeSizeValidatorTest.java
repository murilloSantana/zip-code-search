package com.zipcode.zipcodesearch.usecase.address.validator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ZipCodeSizeValidatorTest {

    @InjectMocks
    private ZipCodeSizeValidator zipCodeSizeValidator;

    @Test
    public void testValidZipCode() {
        String zipCode = "23456789";
        Assert.assertTrue(zipCodeSizeValidator.isValid(zipCode));
    }

    @Test
    public void testSmallInvalidZipCode() {
        String zipCode = "2345678";
        Assert.assertFalse(zipCodeSizeValidator.isValid(zipCode));
    }

    @Test
    public void testLargeInvalidZipCode() {
        String zipCode = "234567822";
        Assert.assertFalse(zipCodeSizeValidator.isValid(zipCode));
    }
}
