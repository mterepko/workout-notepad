package com.maniek.software.workoutnotepad.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OneSelectedValidator.class)
public @interface OneSelected {
    String message() default "At least one option must be selected";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
