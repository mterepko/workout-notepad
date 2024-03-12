package com.maniek.software.workoutnotepad.user;

public class EmailAlreadyExistsException extends Exception {

    public EmailAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
