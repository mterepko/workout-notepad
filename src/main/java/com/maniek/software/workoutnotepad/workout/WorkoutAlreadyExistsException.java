package com.maniek.software.workoutnotepad.workout;

public class WorkoutAlreadyExistsException extends Exception {

    public WorkoutAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
