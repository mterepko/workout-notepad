package com.maniek.software.workoutnotepad.workoutResult;

public class WorkoutResultExistsException extends RuntimeException{

    public WorkoutResultExistsException(String message){
        super(message);
    }
}
