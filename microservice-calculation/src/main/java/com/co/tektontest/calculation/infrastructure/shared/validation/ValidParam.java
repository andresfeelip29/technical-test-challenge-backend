package com.co.tektontest.calculation.infrastructure.shared.validation;

import com.co.tektontest.calculation.infrastructure.shared.validation.validator.ValidParamValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidParamValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface ValidParam {


}
