package com.maniek.software.workoutnotepad.workoutResult;

import java.util.List;

public interface CustomWorkoutResultRepository {

    List<WorkoutResult> findWorkoutResultsByUsername(String username);
}
