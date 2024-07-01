package com.maniek.software.workoutnotepad.workout;

public class WorkoutNameAlreadyExistsException extends RuntimeException {
    public WorkoutNameAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
