package com.maniek.software.workoutnotepad.workoutResult;

public class WorkoutResultNoExistsException extends RuntimeException{

    public WorkoutResultNoExistsException(String message){
        super(message);
    }
}
