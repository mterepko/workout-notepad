package com.maniek.software.workoutnotepad.exercise;

import com.maniek.software.workoutnotepad.validation.OneSelected;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@OneSelected
public class ExerciseRequest {

    @NotEmpty(message = "Name of exercise cannot be empty")
    @Size(max=40)
    private String name;

    private boolean hasReps;

    private boolean hasWeight;

    private boolean hasSeries;

    private boolean hasTime;

    @Size(max=255)
    private String description;
}
