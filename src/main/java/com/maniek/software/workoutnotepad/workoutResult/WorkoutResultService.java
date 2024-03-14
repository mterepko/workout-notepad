package com.maniek.software.workoutnotepad.workoutResult;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkoutResultService {

    private final WorkoutResultRepository workoutResultRepository;



    public List<WorkoutResult> workoutResultsByUsername(String username){

        return workoutResultRepository.findWorkoutResultsByUsername(username);
    }
}
