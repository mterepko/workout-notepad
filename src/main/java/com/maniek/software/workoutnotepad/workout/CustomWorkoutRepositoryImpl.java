package com.maniek.software.workoutnotepad.workout;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class CustomWorkoutRepositoryImpl implements CustomWorkoutRepository{

    private final EntityManager entityManager;

    @Override
    public List<Workout> findWorkoutsByUsername(String username) {

        TypedQuery<Workout> query = entityManager.createQuery(
                "SELECT w FROM Workout w JOIN FETCH w.exercises WHERE w.user.username = :data",
                Workout.class
        );
        query.setParameter("data", username);
        return query.getResultList();
    }
}
