package com.maniek.software.workoutnotepad.user;

import com.maniek.software.workoutnotepad.exercise.Exercise;

import java.util.List;
import java.util.Optional;

public interface CustomUserRepository {

    Optional<User> findByUsername(String username);


}
