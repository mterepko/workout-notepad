package com.maniek.software.workoutnotepad.workoutResult;

import com.maniek.software.workoutnotepad.exerciseResult.ExerciseResult;
import com.maniek.software.workoutnotepad.exerciseResult.ExerciseResultRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WorkoutResultService {

    private final WorkoutResultRepository workoutResultRepository;

    public List<WorkoutResult> workoutResultsByUsername(String username){

        return workoutResultRepository.findWorkoutResultsByUsername(username);
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
            ExerciseResultRequest tempExResultRequest = new ExerciseResultRequest(exResult.getId(), exResult.getRepsCount(),
                    exResult.getWeight(), exResult.getSeriesCount(), exResult.getTime());

            tempExerciseResultRequestList.add(tempExResultRequest);
        }

        WorkoutResultRequest tempWorkoutResultRequest = new WorkoutResultRequest(workoutResult.getName(), workoutResult.getWorkout().getId()
                , workoutResult.getWorkoutDate(), tempExerciseResultRequestList);

        System.out.println(tempWorkoutResultRequest);

        return tempWorkoutResultRequest;
    }

}
