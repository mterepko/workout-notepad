package com.maniek.software.workoutnotepad.exercise;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ExerciseRequest {

    private String name;

    private boolean hasReps;

    private boolean hasWeight;

    private boolean hasSeries;

    private boolean hasTime;

    private String description;
}
