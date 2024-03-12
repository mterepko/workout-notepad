package com.maniek.software.workoutnotepad.workout;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public List<Workout> findWorkoutsByUsername(String username) {

        return workoutRepository.findWorkoutsByUsername(username);
    }

    public List<Workout> findWorkoutsOfOtherUsers(String username) {

        return workoutRepository.findWorkoutsOfOtherUsers(username);
    }

    public Workout findWorkoutById(Long workoutId) {

        return workoutRepository.findWorkoutById(workoutId).orElse(null);
    }
}
