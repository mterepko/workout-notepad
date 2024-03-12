package com.maniek.software.workoutnotepad.workout;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CustomWorkoutRepositoryImpl implements CustomWorkoutRepository {

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

    @Override
    public List<Workout> findWorkoutsOfOtherUsers(String username) {
        TypedQuery<Workout> query = entityManager.createQuery(
                "SELECT w FROM Workout w JOIN FETCH w.exercises WHERE w.user.username != :data",
                Workout.class
        );
        query.setParameter("data", username);
        return query.getResultList();
    }

    @Override
    public Optional<Workout> findWorkoutById(Long workoutId) {
        try {
            TypedQuery<Workout> query = entityManager.createQuery(
                    "SELECT w FROM Workout w JOIN FETCH w.exercises WHERE w.id = :data",
                    Workout.class
            );
            query.setParameter("data", workoutId);

            Workout workout = query.getSingleResult();
            return Optional.of(workout);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
