package com.maniek.software.workoutnotepad.exerciseResult;

import com.maniek.software.workoutnotepad.exercise.Exercise;
import com.maniek.software.workoutnotepad.workoutResult.WorkoutResult;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ExerciseResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int repsCount;

    private double weight;

    private int seriesCount;

    private int timeOfExerciseSeconds;


    @ManyToOne
    private Exercise exercise;

    @ManyToOne
    private WorkoutResult workoutResult;
}
