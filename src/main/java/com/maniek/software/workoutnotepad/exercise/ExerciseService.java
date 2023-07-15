package com.maniek.software.workoutnotepad.exercise;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public String createExercise(ExerciseRequest exerciseRequest){

        Exercise exercise = new Exercise(exerciseRequest.name(), exerciseRequest.hasReps(), exerciseRequest.hasWeight(),
                exerciseRequest.hasSeries(), exerciseRequest.hasTime(), exerciseRequest.description());



        return exerciseRepository.save(exercise).toString();
    }
}
