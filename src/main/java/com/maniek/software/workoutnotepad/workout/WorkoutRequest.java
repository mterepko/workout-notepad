package com.maniek.software.workoutnotepad.workout;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WorkoutRequest {

    @NotEmpty(message = "Name of workout cannot be empty")
    private String name;

    @Size(min = 1, max = 10)
    private List<Long> exerciseIds;

}
