package com.maniek.software.workoutnotepad.user;

public class UsernameAlreadyExistsException extends Exception{

    public UsernameAlreadyExistsException(String errorMessage){
        super(errorMessage);
    }
}
