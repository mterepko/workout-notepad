package com.maniek.software.workoutnotepad.exercise;

import java.util.List;

public interface CustomExerciseRepository {

    List<Exercise> findUsersExercises(String username);
    List<Exercise> findOtherUsersExercises(String username);
}
