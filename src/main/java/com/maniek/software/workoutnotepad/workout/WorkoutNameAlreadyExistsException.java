package com.maniek.software.workoutnotepad.workout;

public class WorkoutNameAlreadyExistsException extends Exception{
    public WorkoutNameAlreadyExistsException(String errorMessage){
        super(errorMessage);
    }
}
