package com.maniek.software.workoutnotepad.exerciseResult;

import com.maniek.software.workoutnotepad.validation.ExerciseResultConstraint;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@ExerciseResultConstraint
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseResultRequest {

    private Long exerciseResultId;

    @NotNull
    private Long exerciseId;

    private Integer repsCount;

    private Double weight;

    private Integer seriesCount;

    private Integer timeOfExerciseSeconds;


}


