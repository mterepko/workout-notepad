package com.maniek.software.workoutnotepad.exerciseResult;

import com.maniek.software.workoutnotepad.validation.ExerciseResultConstraint;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ExerciseResultConstraint
public class ExerciseResultRequest {

    @NotNull
    private Long exerciseId;

    private Integer repsCount;

    private Double weight;

    private Integer seriesCount;

    private Integer timeOfExerciseSeconds;
}
