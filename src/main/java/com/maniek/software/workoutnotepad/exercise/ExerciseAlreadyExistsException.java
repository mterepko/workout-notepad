package com.maniek.software.workoutnotepad.exercise;

public class ExerciseAlreadyExistsException extends Exception {

    public ExerciseAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
