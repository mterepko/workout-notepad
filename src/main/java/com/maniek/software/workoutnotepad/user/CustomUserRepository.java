package com.maniek.software.workoutnotepad.user;

import java.util.Optional;

public interface CustomUserRepository {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserWithWorkoutsByUsername(String username);

    Optional<User> findUserWithExercisesByUsername(String username);
}
