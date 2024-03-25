package com.example.bank.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AccountNumberValidator.class)
public @interface AccountNumberValidation {

    public String message() default "Account Number can not contain characters.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
