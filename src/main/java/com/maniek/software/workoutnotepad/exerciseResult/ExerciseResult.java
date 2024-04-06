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

    private Integer repsCount;

    private Double weight;

    private Integer seriesCount;

    private Integer time;


    @ManyToOne
    private Exercise exercise;

    @ManyToOne
    private WorkoutResult workoutResult;


    public ExerciseResult(Integer repsCount, Double weight, Integer seriesCount, Integer time, Exercise exercise) {
        this.repsCount = exercise.isHasReps() == true ? repsCount : null;
        this.weight = exercise.isHasWeight() == true ? weight : null;
        this.seriesCount = exercise.isHasSeries() == true ? seriesCount : null;
        this.time = exercise.isHasTime() == true ? time : null;
        this.exercise = exercise;
    }
}
