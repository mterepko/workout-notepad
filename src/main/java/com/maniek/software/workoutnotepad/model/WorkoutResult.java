package com.maniek.software.workoutnotepad.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class WorkoutResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    List<ExerciseResult> listOfExerciseResults;

}
