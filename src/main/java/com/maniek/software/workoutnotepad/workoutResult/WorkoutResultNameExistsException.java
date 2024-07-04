package com.maniek.software.workoutnotepad.workoutResult;

public class WorkoutResultNameExistsException extends RuntimeException{

    public WorkoutResultNameExistsException(String message){
        super(message);
    }
}
