package com.zipcode.zipcodesearch.usecase.address.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZipCodeNumberValidator implements ZipCodeValidator {
    @Override
    public boolean isValid(String zipCode) {
        Pattern zipCodeExpectedPattern = Pattern.compile("^[0-9]+$");
        Matcher zipCodeMatcher = zipCodeExpectedPattern.matcher(zipCode);
        return zipCodeMatcher.matches();
    }
}
