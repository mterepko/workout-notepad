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

    public String deleteWorkoutResult(String name, Long id) throws WorkoutResultNoExistsException {

        WorkoutResult workoutResult = workoutResultRepository.findUsersWorkoutResultById(name, id).orElse(null);

        if(workoutResult == null){
            throw new WorkoutResultNoExistsException("There is no such workout result!");
        }
        String workoutResultName = workoutResult.getName();
        workoutResultRepository.delete(workoutResult);

        return workoutResultName;
    }
}
