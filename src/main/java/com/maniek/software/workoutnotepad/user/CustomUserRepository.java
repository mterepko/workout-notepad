package com.maniek.software.workoutnotepad.user;

import com.maniek.software.workoutnotepad.exercise.Exercise;
import com.maniek.software.workoutnotepad.workout.Workout;

import java.util.List;
import java.util.Optional;

public interface CustomUserRepository {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserWithWorkoutsByUsername(String username);

    Optional<User> findUserWithExercisesByUsername(String username);


}
