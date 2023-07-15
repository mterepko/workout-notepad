package com.maniek.software.workoutnotepad.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        // TODO Regex to validate the email and check if it does not already exists
        return true;
    }
}
