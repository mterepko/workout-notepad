package com.maniek.software.workoutnotepad.exercise;

public class ExerciseAlreadyExistsException extends RuntimeException {

    public ExerciseAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
