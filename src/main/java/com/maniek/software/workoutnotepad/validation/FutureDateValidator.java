package com.maniek.software.workoutnotepad.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.util.Date;

public class FutureDateValidator implements ConstraintValidator<FutureDate, LocalDate> {
    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {

        if(date == null){
            return true;
        }
        LocalDate currentDate = LocalDate.now();
        return !date.isAfter(currentDate);
    }
}
