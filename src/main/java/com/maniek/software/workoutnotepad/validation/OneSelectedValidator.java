package com.maniek.software.workoutnotepad.validation;

import com.maniek.software.workoutnotepad.exercise.ExerciseRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OneSelectedValidator implements ConstraintValidator<OneSelected, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value instanceof ExerciseRequest) {
            ExerciseRequest exerciseRequest = (ExerciseRequest) value;
            return exerciseRequest.isHasReps() || exerciseRequest.isHasWeight() ||
                    exerciseRequest.isHasSeries() || exerciseRequest.isHasTime();
        }

        return false;
    }
}
