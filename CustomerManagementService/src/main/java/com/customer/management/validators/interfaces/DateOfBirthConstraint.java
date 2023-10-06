package com.customer.management.validators.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.customer.management.validators.DateOfBirthValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateOfBirthValidator.class)
public @interface DateOfBirthConstraint {
    String message() default "Invalid date of birth format or must be less than 2002";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
