package com.maniek.software.workoutnotepad.exerciseResult;

import com.maniek.software.workoutnotepad.exercise.Exercise;
import com.maniek.software.workoutnotepad.workoutResult.WorkoutResult;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class ExerciseResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int repsCount;

    private double weight;

    private int seriesCount;

    private int time;


    @ManyToOne
    private Exercise exercise;

    @ManyToOne
    private WorkoutResult workoutResult;


    public ExerciseResult(int repsCount, double weight, int seriesCount, int time, Exercise exercise) {
        this.repsCount = repsCount;
        this.weight = weight;
        this.seriesCount = seriesCount;
        this.time = time;
        this.exercise = exercise;
    }
}
