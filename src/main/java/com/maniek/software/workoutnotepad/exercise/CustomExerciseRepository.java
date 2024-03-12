package com.maniek.software.workoutnotepad.exercise;

import java.util.List;
import java.util.Optional;

public interface CustomExerciseRepository {

    Optional<Exercise> findUsersExercise(String username, String exerciseName);

    List<Exercise> findUsersExercises(String username);

    List<Exercise> findOtherUsersExercises(String username);
}
