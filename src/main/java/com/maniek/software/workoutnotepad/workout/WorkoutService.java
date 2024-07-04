package com.maniek.software.workoutnotepad.workout;

import com.maniek.software.workoutnotepad.exercise.Exercise;
import com.maniek.software.workoutnotepad.exercise.ExerciseRepository;
import com.maniek.software.workoutnotepad.exercise.ExerciseService;
import com.maniek.software.workoutnotepad.user.User;
import com.maniek.software.workoutnotepad.workoutResult.WorkoutResult;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final ExerciseService exerciseService;

    public List<Workout> findWorkoutsByUsername(String username) {

        return workoutRepository.findWorkoutsByUsername(username);
    }

    public List<Workout> findWorkoutsOfOtherUsers(String username) {

        return workoutRepository.findWorkoutsOfOtherUsers(username);
    }

    public Workout findWorkoutById(Long workoutId) {

        return workoutRepository.findWorkoutById(workoutId).orElseThrow(()-> new RuntimeException("No such workout"));
    }

    public Workout findWorkoutByWorkoutResultId(Long workoutResultId) {

        return workoutRepository.findWorkoutByWorkoutResultId(workoutResultId).orElse(null);
    }

    @Transactional
    public Workout createWorkout(User user, WorkoutRequest workoutRequest){

        List<Exercise> exerciseList = exerciseService.findAllByIdIn(workoutRequest.getExerciseIds());
        Workout workout = new Workout(workoutRequest.getName(), new Date(), exerciseList);
        validateNewWorkout(user, workout);

        return workoutRepository.save(workout);
    }

    private void validateNewWorkout(User user , Workout newWorkout){

        List<Workout> existingWorkouts = user.getListOfWorkouts().stream()
                .filter(workout -> workout.equals(newWorkout))
                .toList();

        List<Workout> duplicatedNameWorkouts = user.getListOfWorkouts().stream()
                .filter(workout -> workout.getName().equals(newWorkout.getName()))
                .toList();

        if(!existingWorkouts.isEmpty()){
            throw new WorkoutAlreadyExistsException("You already have a workout with those exercises");
        }

        if(!duplicatedNameWorkouts.isEmpty()){
            throw new WorkoutNameAlreadyExistsException("You already have the workout with this name");
        }

    }
}
