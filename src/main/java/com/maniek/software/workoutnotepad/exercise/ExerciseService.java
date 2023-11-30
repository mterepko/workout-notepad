package com.maniek.software.workoutnotepad.exercise;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public List<Exercise> findOtherUsersExercises(String username){

        return exerciseRepository.findOtherUsersExercises(username);
    }


}
