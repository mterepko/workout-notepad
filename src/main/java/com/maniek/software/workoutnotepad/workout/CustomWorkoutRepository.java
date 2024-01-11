package com.maniek.software.workoutnotepad.workout;



import java.util.List;
import java.util.Optional;

public interface CustomWorkoutRepository {

    List<Workout> findWorkoutsByUsername(String username);

    List<Workout> findWorkoutsOfOtherUsers(String username);
    Optional<Workout> findWorkoutById(Long workoutId);
}
