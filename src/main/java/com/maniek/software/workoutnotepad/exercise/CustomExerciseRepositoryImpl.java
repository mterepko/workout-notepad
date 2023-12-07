package com.maniek.software.workoutnotepad.exercise;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CustomExerciseRepositoryImpl implements CustomExerciseRepository {

    private final EntityManager entityManager;

    @Override
    public Optional<Exercise> findUsersExercise(String username, String exerciseName) {
        try {
            Exercise exercise = entityManager.createQuery("SELECT e FROM Exercise e JOIN e.user u "
                    + "WHERE u.username = :username AND e.name = :exerciseName", Exercise.class)
                    .setParameter("username", username)
                    .setParameter("exerciseName", exerciseName)
                    .getSingleResult();

            return Optional.of(exercise);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Exercise> findUsersExercises(String username) {
        try {
            List<Exercise> exercises = entityManager.createQuery(
                            "SELECT e FROM Exercise e JOIN e.user u WHERE u.username = :username "
                                + "ORDER BY e.creationDate DESC", Exercise.class
                    )
                    .setParameter("username", username)
                    .getResultList();

            return exercises;
        } catch (NoResultException e) {
            return List.of();
        }
    }

    @Override
    public List<Exercise> findOtherUsersExercises(String username) {
        try {

            List<Exercise> exercises = entityManager.createQuery(
                            "SELECT e FROM Exercise e JOIN e.user u WHERE u.username <> :username "
                                + "ORDER BY e.creationDate DESC", Exercise.class
                    )
                    .setParameter("username", username)
                    .getResultList();

            return exercises;
        } catch (NoResultException e) {
            return List.of();
        }
    }
}
