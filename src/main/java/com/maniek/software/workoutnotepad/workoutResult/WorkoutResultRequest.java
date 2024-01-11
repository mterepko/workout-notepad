package com.maniek.software.workoutnotepad.workoutResult;

import com.maniek.software.workoutnotepad.exerciseResult.ExerciseResultRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WorkoutResultRequest {

    private String name;

    private List<ExerciseResultRequest> exerciseResults;
}
