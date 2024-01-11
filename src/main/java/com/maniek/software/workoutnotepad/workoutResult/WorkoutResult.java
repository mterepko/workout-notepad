package com.maniek.software.workoutnotepad.workoutResult;

import com.maniek.software.workoutnotepad.exerciseResult.ExerciseResult;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WorkoutResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    List<ExerciseResult> listOfExerciseResults;

    public WorkoutResult(String name, List<ExerciseResult> listOfExerciseResults) {
        this.name = name;
        this.listOfExerciseResults = listOfExerciseResults;
    }
}
