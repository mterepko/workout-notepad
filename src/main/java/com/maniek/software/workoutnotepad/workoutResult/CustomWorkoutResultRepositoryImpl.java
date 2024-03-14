package com.maniek.software.workoutnotepad.workoutResult;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class CustomWorkoutResultRepositoryImpl implements CustomWorkoutResultRepository{

    private final EntityManager entityManager;

    @Override
    public List<WorkoutResult> findWorkoutResultsByUsername(String username) {

        TypedQuery<WorkoutResult> query = entityManager.createQuery(
                "SELECT w FROM WorkoutResult w JOIN FETCH w.listOfExerciseResults er"
                    + " JOIN FETCH er.exercise WHERE w.user.username = :data", WorkoutResult.class
        );
        query.setParameter("data", username);

        return query.getResultList();
    }
}
