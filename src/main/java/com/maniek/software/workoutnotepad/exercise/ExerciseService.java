package com.maniek.software.workoutnotepad.exercise;

import com.maniek.software.workoutnotepad.user.User;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public List<Exercise> findOtherUsersExercises(String username) {

        return exerciseRepository.findOtherUsersExercises(username);
    }

    public List<Exercise> findUsersExercises(String username) {

        return exerciseRepository.findUsersExercises(username);
    }

    public Exercise findById(Long id){

        return exerciseRepository.findById(id).orElseThrow(() -> new RuntimeException("No such exercise."));
    }

    public List<Exercise> findAllByIdIn(List<Long> exercisesIds){

        return exerciseRepository.findAllByIdIn(exercisesIds);
    }

    public void validateNewExercise(User user,Exercise newExercise){

        List<Exercise> existingExercises = user.getListOfExercises().stream()
                .filter(exercise -> exercise.equals(newExercise))
                .toList();

        if(!existingExercises.isEmpty()){
            throw new ExerciseAlreadyExistsException("This user already have this exercise");
        }
    }
    @Transactional
    public Exercise createExercise(ExerciseRequest exerciseRequest, User user){

        Exercise exercise = new Exercise(
                exerciseRequest.getName(),
                exerciseRequest.isHasReps(),
                exerciseRequest.isHasWeight(),
                exerciseRequest.isHasSeries(),
                exerciseRequest.isHasTime(),
                exerciseRequest.getDescription(),
                new Date());

        validateNewExercise(user, exercise);

        return exerciseRepository.save(exercise);
    }

}
