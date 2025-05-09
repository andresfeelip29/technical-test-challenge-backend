package com.co.tektontest.calculation.infrastructure.shared.validation.validator;

import com.co.tektontest.calculation.infrastructure.shared.validation.ValidParam;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class ValidParamValidator implements ConstraintValidator<ValidParam, Double> {

    @Override
    public void initialize(ValidParam constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {

        String ragexNumeric = "^\\d+$";

        if(Objects.isNull(value)) return false;


        return false;
    }
}
