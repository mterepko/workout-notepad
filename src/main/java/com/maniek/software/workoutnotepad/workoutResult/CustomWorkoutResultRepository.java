package com.maniek.software.workoutnotepad.workoutResult;

import java.util.List;
import java.util.Optional;

public interface CustomWorkoutResultRepository {

    List<WorkoutResult> findWorkoutResultsByUsername(String username);

    Optional<WorkoutResult> findUsersWorkoutResultById(String username, Long id);
}
