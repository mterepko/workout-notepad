package com.maniek.software.workoutnotepad.model;

import com.maniek.software.workoutnotepad.exercise.Exercise;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ExerciseResult {

    @Id
    private Long id;

    private int countOfReps;

    private double weight;

    private int countOfSeries;

    private int timeOfExerciseSeconds;

    private int pauseBetweenSeriesSeconds;

    @ManyToOne
    private Exercise exercise;

    @ManyToOne
    private WorkoutResult workoutResult;
}
