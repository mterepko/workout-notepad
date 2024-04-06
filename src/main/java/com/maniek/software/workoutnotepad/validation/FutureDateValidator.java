package com.maniek.software.workoutnotepad.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Date;

public class FutureDateValidator implements ConstraintValidator<FutureDate, Date> {
    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {

        if(date == null){
            return true;
        }
        Date currentDate = new Date();
        return !date.after(currentDate);
    }
}
