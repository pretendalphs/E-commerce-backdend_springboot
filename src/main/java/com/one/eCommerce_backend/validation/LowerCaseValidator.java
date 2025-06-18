package com.one.eCommerce_backend.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LowerCaseValidator implements ConstraintValidator<LowerCase, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true; // Null values are considered valid
        }
        return value.equals(value.toLowerCase()); // Check if the string is in lower case
    }
}
