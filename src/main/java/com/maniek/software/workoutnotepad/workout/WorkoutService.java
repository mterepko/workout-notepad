package com.maniek.software.workoutnotepad.workout;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
}
