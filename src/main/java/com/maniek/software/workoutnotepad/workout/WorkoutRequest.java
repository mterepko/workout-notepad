package com.maniek.software.workoutnotepad.workout;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WorkoutRequest {

    private String name;

    private List<Long> exerciseIds;
}
