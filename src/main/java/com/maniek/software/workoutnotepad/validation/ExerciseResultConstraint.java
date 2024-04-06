package com.maniek.software.workoutnotepad.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExerciseResultValidator.class)
public @interface ExerciseResultConstraint {

    String message() default "Exercise results are not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
