package com.maniek.software.workoutnotepad.exerciseResult;

import com.maniek.software.workoutnotepad.exercise.Exercise;
import com.maniek.software.workoutnotepad.workoutResult.WorkoutResult;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExerciseResult that = (ExerciseResult) o;

        if (!Objects.equals(repsCount, that.repsCount)) return false;
        if (!Objects.equals(weight, that.weight)) return false;
        if (!Objects.equals(seriesCount, that.seriesCount)) return false;
        if (!Objects.equals(time, that.time)) return false;
        return Objects.equals(exercise, that.exercise);
    }

    @Override
    public int hashCode() {
        int result = repsCount != null ? repsCount.hashCode() : 0;
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (seriesCount != null ? seriesCount.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (exercise != null ? exercise.hashCode() : 0);
        return result;
    }
}
