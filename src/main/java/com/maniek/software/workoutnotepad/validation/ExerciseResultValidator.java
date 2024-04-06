package com.maniek.software.workoutnotepad.validation;

import com.maniek.software.workoutnotepad.exercise.Exercise;
import com.maniek.software.workoutnotepad.exercise.ExerciseRepository;
import com.maniek.software.workoutnotepad.exerciseResult.ExerciseResultRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExerciseResultValidator implements ConstraintValidator<ExerciseResultConstraint, Object> {

    private final ExerciseRepository exerciseRepository;
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if(value instanceof ExerciseResultRequest){
            ExerciseResultRequest exerciseResultRequest = (ExerciseResultRequest) value;
            boolean isValid = true;
            Exercise exercise = exerciseRepository.getById(exerciseResultRequest.getExerciseId());

            if (exerciseResultRequest.getRepsCount() == null && exercise.isHasReps()) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate(
                        String.format("Reps count in exercise %s is required", exercise.getName())).addConstraintViolation();
                isValid = false;
            }

            if (exerciseResultRequest.getWeight() == null && exercise.isHasWeight()) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate(
                        String.format("Weight in exercise %s is required", exercise.getName())).addConstraintViolation();

                isValid = false;
            }

            if (exerciseResultRequest.getSeriesCount() == null && exercise.isHasSeries()) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate(
                        String.format("Series count in exercise %s is required", exercise.getName())).addConstraintViolation();
                isValid = false;
            }

            if (exerciseResultRequest.getTimeOfExerciseSeconds() == null && exercise.isHasTime()) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate(
                        String.format("Time of exercise %s is required", exercise.getName())).addConstraintViolation();
                isValid = false;
            }

            return isValid;
        }
        return false;
    }

}
