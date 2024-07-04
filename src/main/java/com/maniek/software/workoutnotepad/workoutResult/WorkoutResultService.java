package com.maniek.software.workoutnotepad.workoutResult;

import com.maniek.software.workoutnotepad.exercise.Exercise;
import com.maniek.software.workoutnotepad.exercise.ExerciseService;
import com.maniek.software.workoutnotepad.exerciseResult.ExerciseResult;
import com.maniek.software.workoutnotepad.exerciseResult.ExerciseResultRequest;
import com.maniek.software.workoutnotepad.user.User;
import com.maniek.software.workoutnotepad.workout.Workout;
import com.maniek.software.workoutnotepad.workout.WorkoutService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WorkoutResultService {

    private final WorkoutResultRepository workoutResultRepository;
    private final ExerciseService exerciseService;
    private final WorkoutService workoutService;

    public List<WorkoutResult> workoutResultsByUsername(String username){

        return workoutResultRepository.findWorkoutResultsByUsername(username);
    }

    @Transactional
    public WorkoutResult createWorkoutResult(User user, WorkoutResultRequest workoutResultRequest){

        Workout workout = workoutService.findWorkoutById(workoutResultRequest.getWorkoutId());
        WorkoutResult workoutResult = new WorkoutResult(workoutResultRequest.getName(), workout,
                workoutResultRequest.getWorkoutDate());

        for(ExerciseResultRequest exerciseResultRequest : workoutResultRequest.getExerciseResults()){

            Exercise exercise = exerciseService.findById(exerciseResultRequest.getExerciseId());
            workoutResult.addExerciseResult(new ExerciseResult(
                    exerciseResultRequest.getRepsCount(),
                    exerciseResultRequest.getWeight(),
                    exerciseResultRequest.getSeriesCount(),
                    exerciseResultRequest.getTimeOfExerciseSeconds(),
                    exercise
            ));

        }
        validateNewWorkoutResult(user, workoutResult);

        return workoutResultRepository.save(workoutResult);
    }

    private void validateNewWorkoutResult(User user, WorkoutResult newWorkoutResult){

        List<WorkoutResult> existingWorkoutResults = user.getListOfWorkoutResults().stream()
                .filter(workoutResult -> workoutResult.equals(newWorkoutResult))
                .toList();

        List<WorkoutResult> duplicatedNameWorkoutResults = user.getListOfWorkoutResults().stream()
                .filter(workoutResult -> workoutResult.getName().equals(newWorkoutResult.getName()))
                .toList();

        if(!existingWorkoutResults.isEmpty()){
            throw new WorkoutResultExistsException("You already have exactly the same workout completed!");
        }

        if(!duplicatedNameWorkoutResults.isEmpty()){
            throw new WorkoutResultNameExistsException("You already have a completed workout with this name!");
        }
    }

    public String deleteWorkoutResult(String name, Long id) throws WorkoutResultNoExistsException {

        WorkoutResult workoutResult = workoutResultRepository.findUsersWorkoutResultById(name, id).orElse(null);

        if(workoutResult == null){
            throw new WorkoutResultNoExistsException("There is no such workout result!");
        }
        String workoutResultName = workoutResult.getName();
        workoutResultRepository.delete(workoutResult);

        return workoutResultName;
    }

    public WorkoutResultRequest getWorkoutResultRequest(String name, Long id) throws WorkoutResultNoExistsException {

        WorkoutResult workoutResult = workoutResultRepository.findUsersWorkoutResultWithExerciseResultsById(name, id).orElse(null);

        if (workoutResult == null) {
            throw new WorkoutResultNoExistsException("There is no such workout result!");
        }

        List<ExerciseResultRequest> tempExerciseResultRequestList = new ArrayList<>();

        for (ExerciseResult exResult : workoutResult.getListOfExerciseResults()) {

            ExerciseResultRequest tempExResultRequest = new ExerciseResultRequest(exResult.getId(),
                    exResult.getExercise().getId(), exResult.getRepsCount(), exResult.getWeight(), exResult.getSeriesCount(),
                    exResult.getTime());

            tempExerciseResultRequestList.add(tempExResultRequest);
        }

        WorkoutResultRequest tempWorkoutResultRequest = new WorkoutResultRequest(workoutResult.getName(), workoutResult.getWorkout().getId()
                , workoutResult.getWorkoutDate(), tempExerciseResultRequestList);

        System.out.println(tempWorkoutResultRequest);

        return tempWorkoutResultRequest;
    }

    @Transactional
    public void updateWorkoutResult(String username, Long workoutResultId,
                                    WorkoutResultRequest workoutResultRequest) throws WorkoutResultNoExistsException {

        WorkoutResult workoutResult = workoutResultRepository.findByIdAndUserUsername(workoutResultId, username)
                .orElseThrow(() -> new WorkoutResultNoExistsException("There is no such workout result!"));

        for(ExerciseResultRequest result : workoutResultRequest.getExerciseResults()){

            Exercise tempExercise = exerciseService.findById(result.getExerciseId());

            ExerciseResult tempExerciseResult = new ExerciseResult(result.getExerciseResultId(), result.getRepsCount(),
                    result.getWeight(), result.getSeriesCount(), result.getTimeOfExerciseSeconds(), tempExercise);

            workoutResult.addExerciseResult(tempExerciseResult);
        }
        workoutResult.setWorkoutDate(workoutResultRequest.getWorkoutDate());

        workoutResultRepository.save(workoutResult);
    }

}
