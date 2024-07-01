package com.maniek.software.workoutnotepad.workout;

public class WorkoutAlreadyExistsException extends RuntimeException {

    public WorkoutAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
