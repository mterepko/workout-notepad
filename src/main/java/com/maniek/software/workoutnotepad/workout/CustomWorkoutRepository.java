package com.maniek.software.workoutnotepad.workout;

import java.util.List;

public interface CustomWorkoutRepository {

    List<Workout> findWorkoutsByUsername(String username);
}
