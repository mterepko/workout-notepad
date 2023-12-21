package com.maniek.software.workoutnotepad.workout;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;



    public List<Workout> findWorkoutsByUsername(String username){

        return workoutRepository.findWorkoutsByUsername(username);
    }
}
