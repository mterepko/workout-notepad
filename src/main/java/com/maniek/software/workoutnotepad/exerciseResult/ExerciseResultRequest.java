package com.maniek.software.workoutnotepad.exerciseResult;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseResultRequest {


    private Long exerciseId;

    private int repsCount;

    private double weight;

    private int seriesCount;

    private int timeOfExerciseSeconds;
}
