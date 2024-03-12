package com.maniek.software.workoutnotepad.workout;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WorkoutRequest {

    @NotEmpty(message = "Name of workout cannot be empty")
    private String name;

    private List<Long> exerciseIds;

}
